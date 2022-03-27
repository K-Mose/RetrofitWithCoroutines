# RetrofitWithCoroutines
Retrofit With Kotlin Coroutines - Retrofit을 이용한 네트워크 통신과 Coroutine 동시성 프로그래밍

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
<br><br>

## <a href="https://kotlinlang.org/docs/multiplatform-mobile-concurrency-and-coroutines.html">Coroutine</a>
코루틴은 비동기식 논-블로킹 기법을 사용할 수 있게 하는 가벼운 스레드(light-weight threads)입니다. 
Kotlin은 다양한 고수준의 coroutine-enabled primitives와 함께 <a href="https://github.com/Kotlin/kotlinx.coroutines">kotlinx.coroutines</a> 라이브러리를 제공합니다. 

코루틴의 기본 컨셉 -
* **동시성vs 병렬처리 (Asynchronous vs. parallel processing)**<br>
  비동기식과 병렬처리는 다릅니다. 코루틴 내에서 작업되는 시퀀스는 지연되었다가 나중에 다시 재개됩니다. 이런 행위는 비동기식(논-블로킹 코드)을 callback과 promise 없이 가능하게 합니다. 이것은 비동기식 처리이지만 코루틴과 관련된 모든 단일 스레드에서 일어날 수 있습니다. 
  단일 스레드에서의 처리, 이것이 병렬코드와 다른점 입니다. 병렬 코드는 서로 다른 스레드 **Multiple thread** 에서 일어나지만, 코루틴을 사용하면 다른 스레드를 쓸 필요 없이 단일 스레드로 처리 가능합니다. 
  
* **스레드 변환을 위한 Dispatcher (Dispatcher for changing threads)**<br>
  코루틴은 코루틴이 실행될 스레드에서 정의된 Dispatcher에 의해서 실행됩니다. Dispatcher를 명시하고 변경하는 수 많은 방법이 있지만 `withContext`를 사용하면 인자값과 코드블록으로 dispatcher를 실행시킬 수 있습니다. Dispatcher에 대해 더 많은 정보는 <a href="https://github.com/K-Mose/Kotlin_Again/blob/master/src/doIt/chapter11/section3/ControlCoroutine.kt">여기</a>를 눌러주세요(또는 <a href="https://kotlinlang.org/docs/coroutine-context-and-dispatchers.html#dispatchers-and-threads">여기</a>).<br>
  다른 스레드에서 작업을 수행하기 위해서, 다른 dispatcher와 코드블록을 실행시켜야 합니다. 일반적으로 dispatcher를 교환과 스레드 작동은 JVM에게 비슷하게 작동하지만, 캡쳐 또는 반환된 데이터의 동결과 관련해서는 좀 다릅니다. 

* **캡쳐된 데이터 동결 (Frozen captured data)**<br>
  코드를 다른 스레드에서 실행시키기 위해서는 언 후에 다른 스레드안에서 실행시키는 `functionBlock`을 넘겨주어야 합니다. 
  ```
  fun <R> runOnDifferentThread(functionBlock: () -> R)
  ```
  <a href="https://kotlinlang.org/docs/multiplatform-mobile-concurrency-overview.html">concurrrency overview</a>에 따르면, 상태(state)는 스레드간 공유가 가능합니다(Kotlin 안에서는 반드시 동결상태여야 함). 함수 인자값이 상태가 되며, 동결 되어지며 캡쳐됩니다. 
  …… <br>
  쉽게 말하자면 코틀린이 functionblock에서 진행되는 동안 블록 내에서 사용되는 객체는 상태가 Capture 되며 Frozen 됩니다.

* **반환된 데이터 동결 (Frozen returned data)**<br>
  서로 다른 스레드에서 반환된 데이터 역시 동결됩니다. 심지어 mutable 상태의 데이터를 반환 할 수 있다고 해도, 반환된 데이터가 변경되는 것을 허용하지 않기 때문에 immutable 데이터를 반환하는 것이 권장됩니다. 

코틀린에서 일반적인 코루틴 사용법을 알고싶다면 <a href="https://github.com/K-Mose/Kotlin_Again/tree/master/src/doIt/chapter11">여기</a>를 눌러주세요.

## Structure 
```
RetrofitWithCoroutines
├─ app
│  └─ src
│     ├─ main
│     │  ├─ AndroidManifest.xml
│     │  ├─ java
│     │  │  └─ com
│     │  │     └─ example
│     │  │        └─ retrofitwithcoroutines
│     │  │           ├─ Albums.kt
│     │  │           ├─ AlbumService.kt
│     │  │           ├─ AlbumsItem.kt
│     │  │           ├─ MainActivity.kt
│     │  │           └─ RetrofitInstance.kt
```
앱의 구조는 상당히 간단합니다. Open API인 https://jsonplaceholder.typicode.com/albums의 데이터를 코틀린에서 사용하기 위해 만든 데이터클래스 AlbumsItem.kt와 AlbumsItem을 리스트화 시키기위한 클래스 Albums.kt, Retrofit 어노테이션을 통해서 종단점을 설정하는 AlbumService.kt interface, Retrofit 인스턴스를 싱글톤으로 생성시키고 반환시키는 RetrofitInstance.kt 그리고 시나리오별로 함수를 실행시킬 MainActivity.kt 입니다. 

### AlbumItem & Albums 
data class는 'JSON to Kotlin class'라는 plugin을 사용하면 쉽게 만들 수 있습니다. 
```kotlin
data class AlbumsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)

class Albums : ArrayList<AlbumsItem>()
```
Albums는 단순히 통신을 통해 AlbumItem이 리스트 형태로 왔을 때 받을 수 있게 만들었습니다. 

### Interface With Url End Potins 
```kotlin
interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums() : Response<Albums>
    
    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int) : Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAblum(@Path(value = "id")albumId:Int) : Response<AlbumsItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album: AlbumsItem) : Response<AlbumsItem>
}
```
각 어노테이션 안에 URL 종단점을 작성하고 코루틴과 병행하기 위해 suspend 함수로 작성합니다. 
각 함수의 반환 타입은 Http의 응답데이터가 담겨있는 Retrofit2의 Response객체 입니다. <br>
JSONPlaceholder 가이드 는 <a href="https://jsonplaceholder.typicode.com/guide/">여기</a>를 참고하세요.

### Retrofit Instance Class
```kotlin
class RetrofitInstance {
    companion object {
    
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
        }.build()    
        
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }    
    }
}        
```
인스턴스 생성 객체는 Singletone으로 사용하기 위해 <a href="https://github.com/K-Mose/Kotlin_Again/blob/master/src/doIt/chapter06/section3/ObjectDeclarationExpression.kt">companion object</a>를 사용합니다. 
우선 네트워크 통신의 로깅을 위해 <a href="https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor#logging-interceptor">HttpLoggingInterceptor</a>를 설정합니다. 
##### HttpLoggingInterceptor <br>
Retrofit은 네트워크 통신을위해 OkHttp를 사용합니다. HttpLoggingInterceptor는 OkHttp의 라이브러리로, 현재 앱 내에서 네트워크의 동작 로그를 보여줍니다. 이것은 네트워크 통신을 이해하는데 아주 유용하고 코드가 정상작동하지 않을 때 문제를 찾도록 도와줍니다.  
HttpLoggingInterceptor내에서 로그 레벨을 성정하여 어떻게 로그를 표출할지 설정할 수 있습니다. 
   * Basic - Log request and response lines only
   * Body - Logs request and response line amd their respective headers and bodies of the network operation.
   * Header - Logs request and response lines and their respective headers
로그 설정이 완료되었으면 클라이언트의 `addInterceptor()`함수로 application interceptor를 등록합니다. <br>
Interceptor에 대해 더 알고싶다면 <a href="https://square.github.io/okhttp/features/interceptors/#interceptors">여기</a>를 클릭하세요.

네트워크 통신이 테스트 기기(혹은 지역에 따라서) 네트워크 통신의 속도와 상태가 다를 수 있기 때문에 Retrofit에 network Timeout을 설정합니다. Client의 builder 안에서 Timeout 함수를 설정합니다. 
  * connectTimeout - 앱이 서버와의 연결 타임아웃 설정 (Retrofit의 기본 설정 시간은 10초)
  * readTimeout - 2개의 데이터 패킷이 도착하는데 걸리는 최대 시간 차 설정
  * writeTimeout - 2개의 데이터 패킷을 서버에 보내는데 걸리는 최대 시간차 설정

Timeout 설정은 인터넷 속도, 서버의 성능 그리고 앱이 설치 될 기기에 성능에 따라 고려해서 설정해야 합니다. 


### Create Insatnace
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        …
        // 인스턴스 생성 
        var retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
        // 통신
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getAlbums()
            emit(response)
        }
        responseLiveData.observe(this) {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumItem = albumList.next()
                    Log.i("MyTag", "${albumItem.id} : ${albumItem.title} / ${albumItem.userId}")
                }
            }
        }        
    }
}    
```
인스턴스를 호출하기 위해 [Retrofit Instance Class](#retrofit-instance-class)에서 Singletone으로 생성했던 getRetrofitInstance() 메소드를 호출하고, 호출된 builder에서 API end-point interface를 구현하기 위해 <a href="https://square.github.io/retrofit/2.x/retrofit/retrofit2/Retrofit.html#create-java.lang.Class-">`create()`</a> 함수 안에 작성한 [Service interface](#interface-with-url-end-potins)를 인자로 넘긴 후 인스턴스를 받습니다. 

[interface](#interface-with-url-end-potins)에 작성된 suspend 함수들이 Coroutine과 함께하기 위해 LiveData를 CoroutineLiveData 객체로 초기화 시킵니다. 
초기화된 livedata 내의 `reService.getAlbums()` 함수에서 네트워크 _GET Request_ 요청을 하고 요청 받은 _Response_ 를 `emit(response)` 하면 `responseLiveData`의 Oberser가 이를 감지하여 `Log.i("MyTag", "…")`로 출력합니다. 

PathVariable, QueryParameter 및 Post의 활용은 <a href="https://github.com/K-Mose/RetrofitWithCoroutines/blob/master/app/src/main/java/com/example/retrofitwithcoroutines/MainActivity.kt">MainActivity.kt</a>를 참고하세요
   
## Result
Response Log|
-----|
<img src="https://user-images.githubusercontent.com/55622345/160280154-166fd334-e422-45fb-90fd-07e5e333ad1f.PNG" width="700px">|
<br>

HttpLoggingInterceptor | 
-----|
<img src="https://user-images.githubusercontent.com/55622345/160280210-943e5c08-98f0-4517-9982-d825052c4873.PNG" width="700px">|
<img src="https://user-images.githubusercontent.com/55622345/160280261-40bc024e-5265-49b5-b920-c67596a0ac31.PNG" width="700px">|


## Dependency
Retrofit -
* com.squareup.retrofit2:retrofit:2.9.0
* com.squareup.retrofit2:converter-gson:2.9.0
* com.squareup.okhttp3:logging-interceptor:4.9.3

Coroutine -
* org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0
* org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0   

Lifecycle : ViewModel&Livedata - 
* androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0
* androidx.lifecycle:lifecycle-livedata-ktx:2.4.0
* androidx.lifecycle:lifecycle-compiler:2.4.0 

## Ref.
https://square.github.io/retrofit/ <br/>
https://square.github.io/okhttp/ <br/>
https://github.com/google/gson <br/>
https://guides.codepath.com/android/consuming-apis-with-retrofit <br/>
https://kotlinlang.org/docs/multiplatform-mobile-concurrency-and-coroutines.html#frozen-returned-data <br/>
https://kotlinlang.org/docs/coroutine-context-and-dispatchers.html#dispatchers-and-threads <br/>
https://jsonplaceholder.typicode.com/ <br/>
https://square.github.io/okhttp/features/interceptors/ <br/>

