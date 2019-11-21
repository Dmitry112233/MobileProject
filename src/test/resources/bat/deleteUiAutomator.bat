@echo off
cd C:\Users\Dmitry\AppData\Local\Android\Sdk\platform-tools
adb devices
adb uninstall io.appium.uiautomator2.server
adb uninstall io.appium.uiautomator2.server.test
Echo.
pause