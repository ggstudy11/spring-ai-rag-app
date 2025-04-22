# spring-ai-rag-app
> a simple demo for studying rag, also for building your easy AI agent

**1. Env**  
- Jdk 17 (the lowest version for spring ai)
- Maven 3.9.9

**2. Dependency**
- Spring Boot 3.2.5 (recommend version)
- Spring ai Bom (manage the version of spring ai tools)
- spring test (not necessary)
- lombok (not necessary)
- spring-ai-model (you can choose your model)
- spring-vector-store (necessary for rag)
- spring-ai-tika-document-reader (necessary for doc parse)

![Dependencies](https://yyhyy-blog.oss-cn-shanghai.aliyuncs.com/3ef2eed336029bd008f0e280b609612.png)

**more detail you can see in pom.xml**

**3. project structure**
![ProjectStructure](https://yyhyy-blog.oss-cn-shanghai.aliyuncs.com/3388ff35bef2b47b6c30b6580516a56.png)

- controller - api
- config - configuration for project
- prompt - place prompt for your agent
- resources - just for test in local

**4. how to run?**  
you should add **application.yaml** in resources folder  
it at least should have:
```yaml
server:
  port: <your-application-port>

spring:
  application:
    name: <your-application-name>

  ai:
    ollama:
      base-url: <your-ollama-url> # mine http://localhost:11434
      chat:
        options:
          model: <your-model> # i use deepseek r1:1.5b
      embedding:
        options:
          model: <your-model> # i use mxbai-embed-large:latest

    vectorstore:
      redis:
        prefix: <your-prefix-name> # "rag:embedding:"
        initialize-schema: true
  data:
    redis:
      host: <your-redis-server-ip>
      password: <your-redis-password> # if set password
```

> notice: to develop a simple rag agent you should have at least:
> 1. chat model like openai, i use ollama to deploy local
> 2. embedding model to add document into vectorDatabase, i use ollama too
> 3. vectorDatabase, i use redis-stack here  
> if you use another model you should change the yaml profile, it can be found in Spring web

then **mvn spring-boot:run**

**5. some tips may help you**

you may search blogs to help you, but most of them are out of date,
Spring-ai update in a lot of aspects, like dependency name:
spring-ai-starter-model-<model-name>, you can find in spring-ai-bom

**6. agent**
![resp](https://yyhyy-blog.oss-cn-shanghai.aliyuncs.com/c3f4d6ddd910e2d4cd6f8ab47211c4a.png)
> i feed a pdf about fruit shop for agent

User: 水果店营业时间？  
Agent: 每天早上8点到晚上10点

it seems model by search for knowledgeDataBase becomes an agent for fruit shop
