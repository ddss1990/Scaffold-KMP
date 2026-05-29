# 如何打包并交付 KMPScaffold 多平台应用

> 类型：How-to Guide  
> 目标读者：项目开发者/运维（有基础命令行经验）  
> 目标：将 Android / Desktop / Web 产物打包并交付给其他用户访问或安装（本地打包与交付，不含 CI/CD）。

## 1. 前提条件

- 在仓库根目录执行命令：`/KMPScaffold`
- 已安装并可用：
  - JDK 11+
  - Android SDK（用于 Android 打包）
  - 当前操作系统对应的桌面打包依赖（用于 Desktop 打包）
- 首次建议先验证编译：

```bash
./gradlew :shared:compileKotlinMetadata \
  :androidApp:compileDebugKotlin \
  :desktopApp:compileKotlin \
  :webApp:compileKotlinWasmJs
```

---

## 2. Android：生成 APK 并交付安装

### 2.1 打包 APK

```bash
./gradlew :androidApp:assembleRelease
```

### 2.2 定位 APK 产物

```bash
ls -lh androidApp/build/outputs/apk/release/
```

常见产物名示例：`androidApp-release.apk`（以实际输出为准）。

### 2.3 交付方式

1. 直接把 APK 文件发给用户（企业 IM、网盘、制品库均可）。
2. 用户在 Android 设备上开启“允许安装未知来源应用”后安装。
3. 若需更高安全性，建议后续补充 release 签名与校验流程（本指南不展开）。

---

## 3. Desktop：生成安装包并交付

`desktopApp` 已配置 Compose Desktop Native Distribution（DMG/MSI/DEB）。

### 3.1 当前系统一键打包（推荐）

```bash
./gradlew :desktopApp:packageDistributionForCurrentOS
```

### 3.2 指定格式打包（按需）

```bash
./gradlew :desktopApp:packageDmg   # macOS
./gradlew :desktopApp:packageMsi   # Windows
./gradlew :desktopApp:packageDeb   # Linux (Debian/Ubuntu)
```

### 3.3 定位 Desktop 产物

```bash
find desktopApp/build/compose/binaries -type f \( -name "*.dmg" -o -name "*.msi" -o -name "*.deb" \) | sort
```

### 3.4 交付方式

- macOS 用户分发 `.dmg`
- Windows 用户分发 `.msi`
- Debian/Ubuntu 用户分发 `.deb`

建议按操作系统分别提供下载链接或制品目录。

---

## 4. Web：构建静态站点并部署访问

`webApp` 同时包含 JS 与 Wasm 目标；可使用 Wasm 生产构建，或兼容分发（Wasm 不支持时回退 JS）。

### 4.1 生产构建（Wasm）

```bash
./gradlew :webApp:wasmJsBrowserProductionWebpack
```

### 4.2 兼容分发（推荐交付）

```bash
./gradlew :webApp:composeCompatibilityBrowserDistribution
```

### 4.3 定位 Web 产物

```bash
find webApp/build/dist -maxdepth 4 -type f | head -n 40
```

常见目录（以实际输出为准）：
- `webApp/build/dist/wasmJs/productionExecutable/`
- `webApp/build/dist/js/productionExecutable/`

### 4.4 部署与访问

将构建目录内容上传到任意静态托管服务（Nginx、对象存储静态网站、内网静态服务器等），并通过 URL 访问。

本地快速验收示例（在产物目录内执行）：

```bash
python3 -m http.server 8080
```

浏览器访问：`http://<host>:8080/`

---

## 5. 本地一次性全平台打包命令

> 说明：桌面安装包格式受当前操作系统限制；Android/Web 可跨平台构建（取决于本机环境）。

```bash
./gradlew \
  :androidApp:assembleRelease \
  :desktopApp:packageDistributionForCurrentOS \
  :webApp:composeCompatibilityBrowserDistribution
```

---

## 6. 常见问题排查

### 6.1 Desktop 不能跨系统直接打包

- 这是预期行为。`.dmg/.msi/.deb` 通常需要在对应 OS 上打包。
- 解决：分别在 macOS/Windows/Linux 机器打包，或后续引入 CI 矩阵。

### 6.2 Web 打开空白或 404

- 检查是否把整个构建目录上传（包含 `*.js`、`*.wasm`、资源文件）。
- 检查静态服务器根路径是否对准构建产物目录。
- 检查服务器 MIME 配置是否支持 `.wasm`。

### 6.3 Android 安装失败

- 检查 APK 是否完整传输。
- 检查设备是否允许安装未知来源。
- 如用于正式分发，请补充签名与渠道策略（本指南范围外）。

