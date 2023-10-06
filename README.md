![rn_image_picker_lib_temp_731c92e4-41dc-43a8-9f57-6fe8271a9e14](/uploads/8582746dbf070425445368452bebec7f/rn_image_picker_lib_temp_731c92e4-41dc-43a8-9f57-6fe8271a9e14.jpg)

---

## 기획 의도

- 사회 생활을 하면서 가족, 친구, 직장 상사 및 동료 등의 경조사를 챙길 일이 많아짐
- 나의 경조사에 누가 얼마만큼의 마음을 표현했는지 기억했다가 보답해야 한다는 부담 있음
- 경조사비 특성 상 통장 내역으로 확인 하기 힘들고, 기억에 의존해야 하는 어려움을 해결하기 위한 서비스

---

## 주요 기능

- 친구
    - 친구 등록
    - 친구 카테고리를 통한 분류
    - 친구 등록 순, 많이 보낸 순, 받은 순 정렬
    - 친구 정보 상세 보기
    - 친구와 주고받은 히스토리 조회
- 히스토리
    - 히스토리 작성
        - 누구와, 금액 혹은 어떤 선물을 어떤 경조사에 주고받았는지 등을 등록
    - 히스토리 조회 수정 삭제

---

## 기술 스택

<aside>
💡 **Server**

</aside>

### `**Back-end server**`

- gradle 8.1.1
- spring boot - 2.7.15 ver.
- jdk - 11 ver.
- querydsl - querydsl-jap:5.0.0 ver.
- JWT - io.jsonwebtoken:jjwt 0.9.1 ver.
- Spring Security - spring boot 버전과 동일
- JPA - spring boot 버전과 동일

### `**DataBase server**`

- MariaDB - 11.0.2 ver.
- MariaDB - **포트 : 3306**

### **`Infra`**

- 웹서버(Nginx)와 was 나누어 두는걸로
- AWS EC2
- Docker
- Jenkins
- Nginx

<aside>
💡 **Web Frontend**

</aside>

- Next.js 13.4.12
- typescript 5.1.6
- recoil 0.7.7
- tailwind 3.3.3
- tanstack/react-query 4.32.0
- react-hook-form 7.45.2
- framer-motion 10.16.4
- react-icons 4.10.1
- react-intersection-observer 9.5.2
- react-aws-s3 1.5.0
- react-spinners 0.13.8
- yup 1.2.0

<aside>
💡 **Mobile**

</aside>

- Jetpack Compost
- Kotlin
- MVVM 패턴

<aside>
💡 **Cooperation**

</aside>

- JIRA
- Notion
- Slack
- Git Lab

---

## 프로젝트 구조

![화면_캡처_2023-10-06_103200](/uploads/674f72c68889c8467630756c77b86977/화면_캡처_2023-10-06_103200.png)


