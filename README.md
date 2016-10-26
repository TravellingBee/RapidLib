#RapidLibs
--------------------------
####一、简介：
一套项目级别成熟稳定的框架实践，集合了自己平时开发中比较常用的一些框架，还有一些工具类和帮助快速创建页面的抽象类，
已经在多个公司项目和我个人的一些项目中使用，有问题欢迎提issue，我会及时查看。

**1.1 Gradle集成**

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

```
dependencies {
    compile 'com.github.MarnonDev:RapidLibs:v1.0.4'
}
```

**1.2 使用RapidLibs后将具备以下功能：**

1. 快速创建常见MainActivity（支持ViewPager方式切换、普通hide/show方式切换）
2. 继承MBasicSwipeActivity创建支持手势返回的Activity
3. 一句代码实现沉浸式状态栏
4. 快速创建适配器（ViewPager、ListView、GridView、RecyclerView、ExpandableListView）
5. 在Fragment可见时才进行网络请求（MBasicFragment/MBasicPagerFragment）
6. 多状态切换（加载中/无网络/空页面/加载错误等）
7. RecyclerView快速创建有下拉刷新/上拉加载的列表（List/Grid），可添加多个Header和Footer
8. Retrofit配合RxJava请求网络，处理数据
9. GlideManager.loadImg(Object);快速加载任何格式的图片
12. 还有常用工具类（NetUtil，ScreenUtil,DeviceUtil,MLog,SP,ToastUtil,StackUtil,KeyBoardUtil）

####二、主要功能
![主要功能](https://github.com/MarnonDev/RapidLibs/blob/master/screenshot/structure.png)

####三、使用方法

#####3.1 adapter（常用适配器封装）

>包括了ListView 、GridView、 ExpandableListView 、ViewPagerAdapter，使用简单，简化逻辑，具体使用方法可以直接看[这里](https://github.com/ThePacific/adapter)，不过有一些建议，就是虽然可以在Activity类里面直接写Adapter也没几句代码，但是我还是建议把Adapter部分的代码单独抽离到一个类中管理。

#####3.2 basic （一些基类BasicActivity，BasicFragment等）

>可以根据自己的实际使用情况继承相对应的类，如果MBasicActivity/MBasicFragment中的方法实现实现不了功能，可以在写一个BaseActivity继承MBasicActivity类，实现一些统一的操作。

>|Activity/Fragment|刷新和加载|滑动返回|使用|
>|:---:|:---:|:---:|:---:|
>|MBasicActivity|O|X|点击切换tab|
>|MBasicSwipeActivity|O|O|滑动切换tab|
>|MBasicFragment|O|X|非ViewPager中|
>|MBasicPagerFragment|O|X|ViewPager中|

#####3.3 data （数据操作相关，如网络请求、通用实体类等）

>主要是Retrofit配合RxJava的使用

#####3.4 module （常用模块封装，如RapidMainActivity，RapidPagerMainActivity）
>现在主要是对APP主页面的一个封装，其实还是主要用到了 [FlycoTabLayout](https://github.com/H07000223/FlycoTabLayout) 的这个库，这个库可以实现很多页面布局效果，还支持tab未读消息的标注，很强大！大家可以直接到该项目的主页去看一下。
>使用时，只需要继承对应效果的类即可，不用再自己创建MainActivity的布局，但是需要在AndroidManifest.xml中注册

>|RapidMainActivity|RapidPagerMainActivity|
>|:---:|:---:|
>|点击切换Fragment（类似QQ）|滑动或点击切换Fragment（类似微信）|
>|![](https://github.com/MarnonDev/RapidLibs/blob/master/screenshot/MainBasicActivity.gif)|![](https://github.com/MarnonDev/RapidLibs/blob/master/screenshot/MainPagerBasicActivity.gif)|

```
public class MainActivity extends RapidPagerMainActivity {
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    /**
     * 添加tab名字
     *
     * @return 保存了tab名字的数组
     */
    @Override
    public String[] getTabNameArray() {
        return new String[]{"新闻", "视频", "图片", "我的"};
    }
	/**
     * 添加tab图标（未选中）
     *
     * @return 保存了tab（未选中）图标的数组
     */
    @NonNull
    @Override
    public int[] getTabUnselectedIcon() {
        return new int[]{R.drawable.ic_news, R.drawable.ic_video,
                R.drawable.ic_image, R.drawable.ic_me};
    }
	/**
     * 添加tab图标（选中）
     *
     * @return 保存了tab（选中）图标的数组
     */
    @NonNull
    @Override
    public int[] getTabSelectedIcon() {
        return new int[]{
            R.drawable.ic_news_selected, R.drawable.ic_video_selected,
                    R.drawable.ic_image_selected, R.drawable.ic_me_selected};
    }
	/**
     * 添加Fragment
     *
     * @return 保存fagment的集合
     */
    @NonNull
    @Override
    public ArrayList<Fragment> initFragments() {
        mFragments.add(FirstFragment.newIns());
        mFragments.add(SecondFragment.newIns());
        mFragments.add(ThirdFragment.newIns());
        mFragments.add(FirstFragment.newIns());
        return mFragments;
    }
	/**
     * 如果需要手动修改tab的一些属性，可以在这里设置
     * 比如控制字体大小，显示未读消息，更改字体颜色等
     */
    @Override
    public void setTab() {

    }
}
```

#####3.5 manager （三方库二次封装）

>|类名|功能|主要方法|
>|:---:|:---:|:---:|
>|GlideManager|图片加载Glide封装|— setRoundPlaceholder(int roundPlaceholder);<br>— setCommonPlaceholder(int commonPlaceholder)<br>— loadImg(Object obj, ImageView iv)<br>— loadRoundImg(Object obj, ImageView iv)|

#####3.7 widgets （自定义控件）
>|名称|功能|
>|:---:|:---:|
>|TintStatusBar|快速沉浸状态栏，在集成MBasicActivity之后，重写getStatusBarColor()即可<br>返回0则不适配

####四、感谢

>感谢为开源界做出贡献的各位大神们！啥也不说了，直接献上膝盖吧。因为有你们的无私分享才使得我们可以战在巨人的肩膀上前进！
>* 网络请求 Retrofit 配合 RxJava、RxAndroid
>* 注解绑定Butterknife
>* [ThePacific](https://github.com/ThePacific) 的万能适配器 [adapter](https://github.com/ThePacific/adapter)
>* [H07000223](https://github.com/H07000223) 的 [FlycoTabLayout](https://github.com/H07000223/FlycoTabLayout) 
>* [Jude95](https://github.com/Jude95) 的滑动返回Activity库 [SwipeBackHelper](https://github.com/Jude95/SwipeBackHelper)
>* 
>* 还有一些好用的工具类，来自网络，再次感谢他们的无私分享

####五、关于我 Marno

- 邮箱：marnodev@163.com
- 简书：[点击关注Marno的简书](http://www.jianshu.com/users/174a09ba6c25)
