# Java Docker Registry client library
[![Build Status](https://travis-ci.org/kiryam/registry_client.svg?branch=master)](https://travis-ci.org/kiryam/registry_client)


Example:
```java
Client client = BaseClient();
DockerImageInfo imageInfo = client.getImageInfo("library/nginx");
List<String> tags = imageInfo.getTagInfo().getTags();
```


## TODO
- Authenticate with login and password for access private repositories


## Know issues
