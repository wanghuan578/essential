

## 基于zookeeper的服务治理框架



### 一键安装

- mvn clean install



### 采用框架及功能

#### 框架

- 网络模型netty
- 采用libtba二进制协议
- 过程协同zookeeper
- SDK支持java和c++

#### 功能

- 注册服务
- 获取服务列表
- 服务变更通知
- 获取服务状态信息












### 查询RestApi

```
@RequestMapping("/provider")
public class ProviderServiceController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/application", method = RequestMethod.GET)
    public ResultEntity getServicesInfo() {
        ResultEntity entity = new ResultEntity();
        try {
            return entity.succeed(applicationService.getApplicationInfo());
        } catch (IllegalAccessException | TbaException | InstantiationException e) {
            return entity.failed(UNEXPECTED_EXCEPTION.setTextDefault(e.getMessage()));
        } catch (MainStageException e) {
            return entity.failed(e.getResultType());
        }
    }

    @RequestMapping(value = "/application/detail", method = RequestMethod.POST)
    public ResultEntity getApplicationDetail(@RequestBody ServiceInfoDetailRequest req) {
        ResultEntity entity = new ResultEntity();
        try {
            return entity.succeed(applicationService.getApplicationDetail(req));
        } catch (IllegalAccessException | TbaException | InstantiationException e) {
            return entity.failed(UNEXPECTED_EXCEPTION.setTextDefault(e.getMessage()));
        }
        catch (MainStageException e) {
            return entity.failed(e.getResultType());
        }
    }
}
```



### 相关资源

- [客户端SDK](https://github.com/wanghuan578/essential-sdk)(essential-sdk)



### 作者和贡献者信息


- spirit(57810140@qq.com)

