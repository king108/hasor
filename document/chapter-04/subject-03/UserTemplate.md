&emsp;&emsp;自定义模板引擎。

&emsp;&emsp;下面我们简单的介绍一下 Hasor 的自定义模板引擎配置方式。首先，接入一个渲染引擎需要实现 RenderEngine 接口，如下：
```java
public class UserRender implements RenderEngine {
    public void initEngine(AppContext appContext) throws Throwable {
        ...
    }
    public boolean exist(String template) throws IOException {
        ...
    }
    public void process(RenderInvoker renderData, Writer writer) throws Throwable {
        ...
    }
}
```

&emsp;&emsp;其中，initEngine 方法是整个渲染引擎的初始化方法，它只会执行一次。然后是 exist 方法，它是在执行模板之前进行调用。用于判断渲染引擎是否支某个资源。当 exist 正确返回 true 之后，视图框架就会正式调用 process 进行页面的渲染。此时开发者只需要把模板的执行结果输出到 writer 中即可在浏览器中显示出来。

&emsp;&emsp;如果 exist 返回了 false。 那么框架会将 template 这个资源的渲染交给 Servlet 容器。这就意味着，一旦 exist 为 true 那么 Servlet 容器就不会有机会处理这个模板资源的渲染工作。

&emsp;&emsp;最后当渲染器开发完毕，您还需要将它注册到 Hsor 容器中。您有两种方式进行注册：

&emsp;&emsp;第一种，注解方式：
```java
@Render({ "html", "htm" })
public class UserRender implements RenderEngine {
    ...
}
// -----
public class StartModule extends WebModule {
    public void loadModule(WebApiBinder apiBinder) throws Throwable {
        //扫描所有 Render 注解
        apiBinder.scanAnnoRender();
    }
}
```

&emsp;&emsp;第二种，代码方式注册。此时不需要 @Render 注解：
```java
public class UserRender implements RenderEngine {
    ...
}
// -----
public class StartModule extends WebModule {
    public void loadModule(WebApiBinder apiBinder) throws Throwable {
        apiBinder.suffix("html", "htm").bind(UserRender.class);
    }
}
```