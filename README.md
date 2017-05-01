**写在前面**

其实已经有很多类似的开源库，比较满意的是这个库：https://github.com/qiushi123/GuideView-master，
但它实现的效果和使用方式不是很6，本来是想对这个库提交PR，但发现改动有点小多，所以自己另写了这个库。

同时鸿神的这个库https://github.com/hongyangAndroid/Highlight 也不错，但可能我用的姿势不对，出现了找不到目标view坐标的问题，似乎也不支持fragment。

本开源库主要借鉴学习了这两个库的思路，在此向它们的作者们致谢！

**demo演示**

![](https://github.com/Sbingo/GuideView/raw/master/gif/guide.gif)

**主要功能**

 1. 提供【知道了】按钮，可简单地传入文字说明即可，点击【知道了】自动跳转
 2. 也可不用提供的【知道了】view，传入自定义的view，但需要在你希望的地方调用showNext()方法使引导视图在点击后继续跳转，详见demo。
 3. 构造方法中传入目标版本号，引导视图只会在目标版本中显示，不用担心app升级后会显示。
 4. 每个引导视图只会显示一次，根据版本号和目标viewId判断，所以不能有两个相同id的目标view。
 5. 记忆未显示的引导视图。示例：引导3、4、5在引导2后，显示引导2后退出app，下次到此位置会从引导3开始。
 6. 目标view3种显示形状、目标view和提示view8种相对位置、偏移量自定义
 7. 链式调用，丝般顺滑~

**使用方法**
 - 在项目的根 build.gradle 文件中添加：
 

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

 - 在模块中添加依赖：
 

```
compile 'com.github.Sbingo:GuideView:v1.0.5'
```

 - 在代码中使用
```
//可选
TextView tv = new TextView(this);
        tv.setText("自定义提示view");
        tv.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tv.setTextSize(20);
        tv.setGravity(Gravity.CENTER);

        //测试时用这个方法
//        final GuideView.Builder builder = new GuideView.Builder(this, "1.0", true);
        //正式环境用这个方法，每个GuideView只会显示一次
        final GuideView.Builder builder = new GuideView.Builder(this, "1.0");
	//设置提示文字大小
	builder.setTextSize(20)；
        //添加8个引导视图
        builder.addHintView(text1, "测试1", GuideView.Direction.TOP, GuideView.MyShape.ELLIPSE, -50, -200)
                .addHintView(text2, "测试2", GuideView.Direction.RIGHT_BOTTOM, GuideView.MyShape.RECTANGULAR)
                .addHintView(text3, "测试3", GuideView.Direction.LEFT_BOTTOM, GuideView.MyShape.CIRCULAR)
                .addHintView(text4, "测试4", GuideView.Direction.RIGHT_TOP, GuideView.MyShape.ELLIPSE)
                .addHintView(text5, "测试5", GuideView.Direction.LEFT_TOP, GuideView.MyShape.CIRCULAR)
                .addHintView(text6, "测试6", GuideView.Direction.RIGHT, GuideView.MyShape.CIRCULAR)
                .addHintView(text7, "测试7", GuideView.Direction.LEFT, GuideView.MyShape.CIRCULAR)
                .addHintView(text8, tv, GuideView.Direction.BOTTOM, GuideView.MyShape.RECTANGULAR, -100, 100, new GuideView.OnClickCallback() {
                    @Override
                    public void onGuideViewClicked() {
	                     /*
				        自定义视图需要调用showNext()方法使引导视图在点击后继续跳转
				        调用处可以在点击自定义视图上某处时，不一定要在这里，这里只是示例
				         */
                        builder.showNext();
                    }
                });
        builder.show();
```
提供了6种`addHintView`方法，每调用一次该方法就是增加了一个引导视图。

主要参数为目标view，提示文字或提示view，提示相对于目标view的位置，高亮区域形状（圆形、矩形或椭圆）、偏移量、点击监听。

**ChangeLog**

v1.1.0 可以设置目标版本，只在目标版本中显示

v1.0.5 增加改变文字大小的api

v1.0.0 初始版本
