# spring-todoList

### 학습목표
1. 회원가입, 로그인을 구현할 수 있어요.
2. 인증/인가를 이해하고 JWT를 활용하여 할일 및 댓글을 처리할 수 있어요.
3. JPA 연관관계를 이해하고 회원과 할일 그리고 댓글을 구현할 수 있어요.
4. 할일을 완료처리하며 상태를 관리 할 수 있어요.


### 필수 요구 사항
- [x]  **🆕 회원 가입 API**
    - username, password를 Client에서 전달받기
    - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다.
    - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기
- [x]  **🆕 로그인 API**
    - username, password를 Client에서 전달받기
    - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
    - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 
    발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기
- [x] 할일카드 작성 기능 API**  
    - 토큰을 검사하여, 유효한 토큰일 경우에만 할일 작성 가능
    - `할일 제목`,`할일 내용`, `작성일`을 저장할 수 있습니다. 
    - 할일 제목, 할일 내용을 저장하고
    - 저장된 할일을 Client 로 반환하기(username은 로그인 된 사용자)
- [x]  **선택한 할일카드  조회 기능 API**
    - 선택한 할일 의 정보를 조회할 수 있습니다.
        - 반환 받은 할일 정보에는 `할일 제목`,`할일 내용`, `작성자` , `작성일`정보가 들어있습니다.
- [x]   할일카드 목록 조회 기능 API**
    - 등록된 할일 전체를 조회할 수 있습니다.
        - 회원별로 각각 나누어서 할일 목록이 조회됩니다.
        - 반환 받은 할일 정보에는 `할일 제목`, `작성자` , `작성일`, `완료 여부`정보가 들어있습니다.
    - 조회된 할일 목록은 `작성일` 기준 내림차순으로 정렬 되어있습니다.
- [x]  **선택한 할일카드 수정 기능 API**
    - 선택한 할일카드의 `제목`, `작성 내용`을 수정할 수 있습니다.
        - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
        - 할일 제목, 할일 내용을 수정하고 수정된 할일 정보는 Client 로 반환됩니다.
    - 수정된 할일의 정보를 반환 받아 확인할 수 있습니다.
        - 반환 받은 할일 정보에는 `할일 제목`,`할일 내용`, `작성자` , `작성일`정보가 들어있습니다.
- [x]  **🆕 할일카드 완료 기능 API**
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 할일카드 만 완료 가능
    - 완료처리 한 할일카드는 목록조회시 `완료 여부`필드가 TRUE 로 내려갑니다.
    - `완료 여부` 기본값은 FALSE
- [x]  **🆕 댓글 작성 API**
    - 토큰을 검사하여, 유효한 토큰일 경우에만 댓글 작성 가능
    - 선택한 할일의 DB 저장 유무를 확인하기
    - 선택한 할일이 있다면 댓글을 등록하고 등록된 댓글 반환하기
- [x]  **🆕 댓글 수정 API**
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 수정 가능
    - 선택한 댓글의 DB 저장 유무를 확인하기
    - 선택한 댓글이 있다면 댓글 수정하고 수정된 댓글 반환하기
- [x]  **🆕 댓글 삭제 API**
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 삭제 가능
    - 선택한 댓글의 DB 저장 유무를 확인하기
    - 선택한 댓글이 있다면 댓글 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기
- [x]  **🆕 예외 처리 (ResponseEntity 사용)** 
    - 토큰이 필요한 API 요청에서 토큰을 전달하지 않았거나 정상 토큰이 아닐 때는 "토큰이 유효하지 않습니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
    - 토큰이 있고, 유효한 토큰이지만 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 “작성자만 삭제/수정할 수 있습니다.”라는 에러메시지와 statusCode: 400을 Client에 반환하기
    - DB에 이미 존재하는 username으로 회원가입을 요청한 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
    - 로그인 시, 전달된 username과 password 중 맞지 않는 정보가 있다면 "회원을 찾을 수 없습니다."라는 에러메시지와 statusCode: 400을 Client에 반환하기

### 1. Use Case Diagram
링크 : [ https://app.diagrams.net/#G184is9FzfZg01rvKNC-hzMSh6N2J7RIL1](https://app.diagrams.net/#G1w-yTKrhe7rm7et-B48s5Gukn-S5If0f3)


### 2. ERD
링크 : [https://www.erdcloud.com/d/gTEftvyRw8TESBFqx](https://www.erdcloud.com/d/b28KKXacusbTbv2aS)


### 3. API 명세서
링크 : [https://documenter.getpostman.com/view/30872998/2s9YXfb3Wf#9670828e-196c-4bd9-9945-e29e46eca95b](https://documenter.getpostman.com/view/30872998/2s9Ye8gF4d)https://documenter.getpostman.com/view/30872998/2s9Ye8gF4d

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

### 학습목표
1. Controller 를 테스트 할 수 있어요.
2. Service 를 테스트 할 수 있어요.
3. Repository 를 테스트 할 수 있어요.
4. Mockito 를 사용해서 테스트용 객체를 만들 수 있어요.

### 요구사항
1. profile 을 설정해서 구현해주세요.
2. Mockito 를 적용하여 레이어간 의존성을 격리시켜주세요.

![](https://velog.velcdn.com/images/jac01122/post/7deea85f-8752-416d-8f9f-e0843cab7215/image.png)

### 필수 요구 사항
- [X]  **🆕 DTO, Entity Test 추가하기**
    - `@Test` 를 사용해서 DTO 와 Entity Test 를 추가합니다.
    - User, Todo, Comment, DTO 에 존재하는 메서드들에 대해서 테스트를 추가합니다.
- [X]  **🆕 Controller Test 추가하기**
    - `@WebMvcTest` 를 사용하여 Controller Test 를 추가합니다.
    - Todo, Comment Controller 에 대해서 테스트를 추가합니다.
- [X]  **🆕 Service Test 추가하기**
    - `@ExtendWith` 를 사용하여 Service Test 를 추가합니다.
    - User, UserDetails, Todo, Comment Service 에 대해서 테스트를 추가합니다.
- [X]  **🆕 Repository Test 추가하기**
    - `@DataJpaTest` 를 사용하여 Repository Test 를 추가합니다.
    - User, Todo, Comment Repository 에 대해서 테스트를 추가합니다.
