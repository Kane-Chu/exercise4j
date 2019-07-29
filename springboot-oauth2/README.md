## spring boot + spring security + oauth2 Server

* 授权码模式
* 简单模式
* 密码模式
* 客户端模式

### 授权码模式

1. 获取授权码

    浏览器访问
    ```
    [GET] http://localhost:8080/oauth/authorize?client_id=kane-app&redirect_uri=http://localhost:9090/callback&response_type=code&scope=read_user_info
    ```

2. 获取access-token

    * uri
    
    ```
    [POST] http://kane-app:pass1234@127.0.0.1:8080/oauth/token
    ```
    
    * header
    
    ```
    Content-Type: application/x-www-form-urlencoded
    ```
    
    * body
    
    ```
    code: {第一步获取到的code}
    grant_type: authorization_code
    redirect_uri: 通第一步中的redirect_uri
    scope: 同第一步中的scope
    ```

3. 携带access-token调用资源服务器接口

    * uri
    
    ```
    [GET] http://localhost:8080/api/users/current
    ```
    
    * header
    
    ```
    authorization: Bearer {access-token}
    ```

