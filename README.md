# FullScreenInput


完美解决全屏键盘遮挡输入框问题！！！！！
网上很多方法都是通过LayoutParams来设置位置，会出现机型和版本兼容问题！！


代码很简单，引用AndroidBug5497Workaround的方法监听软键盘的状态和高度，然后通过setTranslationY来设置底部输入框的高度。