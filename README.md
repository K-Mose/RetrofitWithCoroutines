# RetrofitWithCoroutines
Retrofit With Kotlin Coroutines

## <a href="https://square.github.io/retrofit/">Retorfit</a>
Retrofit은 Android, Java 그리고 Kotlin 개발자들을 위한 type-safe REST client 라이브러리 입니다. 
이 라이브러리는 OkHttp를 통한 API와 network request의 인증과 상호작용에 대한 강력한 프레임워크를 제공합니다. 
Retrofit은 쉽게 JSON이나 XML을 API로부터 받은 후 Plain Old Java Object(POJO)로 변환시킵니다. 

#### <a href="https://square.github.io/okhttp/">OkHttp</a>
HTTP는 현대 네트워크 통신 중 한 방법입니다. OkHttp는 효과적인 Http client 입니다. 
* HTTP/2를 지원하며 모든 request들이 같은 host내에서 socket으로 공유가능하도록 허용합니다. 
* Connection pooling으로 요청 지연시간을 단축시킵니다.(HTTP/2가 이용 불가 시)
* 투명한 GZIP으로 다운로드 사이즈를 줄입니다. 
* 응답 캐싱으로 반복적인 요청에대한 네트워크를 완전히 차단합니다. 

OkHttp는 네트워크에 문제가 있을 때 연결문제가 회복 될 때 까지 참고 기다립니다. 사용자가 여러 IP를 서비스할 때 첫 연결 실패 시 OkHttp는 다른 주소로 연결을 시도할 것 입니다. 
OkHttp를 사용하는 것은 쉽습니다. OkHttp의 request/response API는 builder와 immutablility에 능수능란하도록 설계되었습니다. 이것은 동기식과 비동기식의 호출 둘 다 지원합니다.  …  

### Configuration
Retrofit는 API interface를 통해서 호출 가능한 객체로 변환시키는 클래스입니다. 기본적으로 Retrofit은 정상적인 default를 제공하지만 이것은 커스텀 할 수 있습니다. 

### Converter
Retrofit은 HTTP Body OkHttp의 ResponseBody 타입 으로만 역직렬화 가능하며 @Body 어노테이션이 붙은 RequestBody 타입만 수용할 수 있습니다. 
Converter를 사용하면 객체의 직렬화/역직렬화를 손쉽게 가능하게 합니다. 과거에는 GSON만 사용 하였지만 지금은 아래와 같이 다양한 Converter를 지원합니다.  
* Gson: com.squareup.retrofit2:converter-gson
* Jackson: com.squareup.retrofit2:converter-jackson
* Moshi: com.squareup.retrofit2:converter-moshi
* Protobuf: com.squareup.retrofit2:converter-protobuf
* Wire: com.squareup.retrofit2:converter-wire
* Simple XML: com.squareup.retrofit2:converter-simplexml
* JAXB: com.squareup.retrofit2:converter-jaxb
* Scalars (primitives, boxed, and String): com.squareup.retrofit2:converter-scalars


#### <a href="https://github.com/google/gson">GSON</a>
Gson은 java 객체를 json 형태로 변환(또는 json을 java 객체로 변환)시키는 라이브러리 입니다.
여러 다른 라이브러리(java ↔ json)들이 존재하지만, 대부분이 클래스에 코드에 접근하기 위한 Java 어노테이션을 필요로 합니다. 그리고 대부분은 Java의 Generic을 지원하지 않습니다. 
Gson은 이 두 가지를 가장 중요한 설계 목적으로서 여깁니다.<br>
**Goals**
* `toJson()`과 `fromJson()` 메소드 지원으로 간단하게 java ↔ json 변환
* 불변객체에 대한 JSON 변환 허용
* Java Generic에 대한 확장성 
* 객체에 대한 사용자지정 표현 허용
* (깊은 상속 계층이나 제너릭 타입의 확장 등과 같은)복잡한 객체 지원


## Ref.
https://square.github.io/retrofit/
https://square.github.io/okhttp/
https://github.com/google/gson
https://guides.codepath.com/android/consuming-apis-with-retrofit
