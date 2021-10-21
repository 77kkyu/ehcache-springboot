# Description
- auth : 77kkyu
- development environment : spring-boot
- technology : ehcache

> ehcache를 사용하여 cache 저장 API 및 삭제 API를 만들었습니다
> 
> Dockerfile 생성 후에 이미지로 만들어 Docker 컨테이너에 올려서 사용해봤습니다
> 
> [docker로 실행시키는 문서입니다](http://www.google.co.kr)

<br>

---

# ehcache

ehcache는 Spring에서 간단하게 사용할 수 있는 오픈소스 캐시 라이브러리입니다

예를 들어 포털사이트 메인에 노출되어있는 뉴스나 오늘의 글 같은 데이터들을 시간을 정해서 캐시 하기 좋습니다

캐시를 사용할 때에는 항상 고려를 하고 사용해야 합니다

반복적으로 동일한 데이터를 돌려주는 작업을 할 때 같은 경우!

ehcache 2.xx버전과는 달리 3버전에서는 offheap이라는 저장 공간을 제공하고

offheap은 heap메모리를 벗어난 메모리로 GC의 관리 영역에 벗어난 공간입니다

(GC는 heap메모리를 관리합니다)

<br>

- 캐시 저장
> @Cacheable(value = "~", key="number", condition = "#number > 10")

- 캐시 삭제
> @CacheEvict(value = "~", key="~")

<br>

- value는 ehcache.xml에서 등록했던 캐시 중 메서드에 적용할 캐시의 이름(ehcache.xml의 cache 요소에 등록했던 alias 값)을 등록한다


- key는 캐시를 구분하기 위한 용도로 사용된다. 만약 캐시를 구분할 필요가 없다면 key를 등록하지 않는다


- condition은 캐시 처리에 대한 조건을 지정한다. 위에서는 condition을 #number > 10으로 지정 했으므로 이를 해석하면 매개변수 Long number가 10보다 큰 경우에만 캐시 처리를 한다는 뜻이다