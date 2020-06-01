# Wimmy-WhereIsMyMemory

# 프로젝트 개요

![1](https://user-images.githubusercontent.com/57826388/77233868-c4b77500-6bed-11ea-8926-f8753cbbca0d.png)


기존의 사진 관리 프로그램은 보통 폴더별로만 관리할 수 있다. 본 프로그램은 폴더, 특징, 날짜, 위치 별 관리가 가능하다​.  
본 프로그램의 핵심인 태그는 이미지를 분석하여 자동으로 추출하거나, 사용자가 직접 추가, 수정, 삭제가 가능하다.​  
이를 활용하여 지도를 직접 돌아보며 특징을 확인할 수 있도록 분류된 사진에 중첩하여 태그에 대한 연관성을 극대화 하였다.  
또한 사용자가 달력을 통해 특정 날짜의 사진들을 직접 확인하며 개인 일정을 관리할 수도 있다.  
이를 통해 사용자의 사진들에 대한 접근성을 극대화 하는 것이 우리의 최종 목표이다.

- 서비스: 이미지 태그 및 특징/위치/날짜 분류를 통한 안드로이드 사진 관리 애플리케이션
- 개발 환경: Android Studio 3.5/3.6
- 개발 언어: Kotlin, XML
- 기타 환경: Git 
- 외부 자원: Firebase ML Kit - Translate API, Image labeling API, Google Map API
- 관련 기술: Multi Thread, MVVM, Room, Livedata, MediaStore, API
- 제약 사항: 안드로이드 OS 4.0 이상, 기기 접근 권한

<br>

# Diagram

![Untitled Diagram (1)](https://user-images.githubusercontent.com/57826388/77235305-f5041100-6bf7-11ea-998d-5426f7a27c8c.png)

<br>

# 프로젝트 모델링

 안드로이드 내부 DB를 통해 로컬 저장소에 있는 이미지들과 핵심 메타데이터들을 관리한다.  
 추가적으로 관리할 DB는 다음과 같다.

 안드로이드 RoomDB, LiveData, ViewModel 을 이용하여 `MVVM모델`을 구축.  
 `Observer` 패턴을 통해 이미지의 변경이 있을 시 자동으로 업데이트를 하도록 한다.

- ExtraPhotoData
    - 사진 id
    - 위치
    - 즐겨찾기 여부

- TagData
    - 사진 id
    - 태그

- thumnailData
    - 사진 id
    - 데이터

- LatLngData
    - 위경도 번호
    - 사진 아이디
    - 위경도
    
- checkboxData
    - 사진 아이디
    - 체크 정보
    
- CalendarData
    - 날짜
    - 주제
    - 메모
    
<br>

# 주요 일정

## 완료

- ~ 2020\. 02. 07: 시나리오, 기능 도출, 프로토타입 UI 개발

- 2020\. 02. 08 ~ 2020\. 02. 14: XML UI 구조 개발, DB 모델링  

- 2020\. 02. 14 ~ 03. 21: 주요 개발 (Android Studio)
    - 사진을 폴더, 태그, 위치, 날짜별로 보여주기​
    - 안드로이드 내부 DB에서 사진 목록 가져오기​
    - 날짜별로 보여주기 위한 캘린더 만들기​
    - Room을 사용해 추가 정보 저장(좌표 -> 위치 정보, 즐겨찾기, 태그 정보)​
    - Firebase ML Kit - 이미지 분석 API를 활용하여 이미지 특징정보 추출​
    - 사진 출력을 위한 UI 생성
    - 구글 맵 API와 클러스터링 라이브러리를 활용하여 위치 좌표 생성 및 출력​
    - 프로그램 시작 시 추가 삭제된 사진 처리​
    - ThreadPoolExcutor를 사용한 추가 쓰레드 관리
    - 사용자가 직접 태그를 입력하거나 수정, 삭제 가능​
    - 사진 공유기능​
    - 백그라운드에서 특징정보 자동 분석 및 삽입​
    
- 2020\. 03. 22 ~ 04. 05: 추가 기능 도출 및 버그 픽스
    - 기타 버그 수정
    - 이미지 리스트 그리드 값 조절 기능
    - 유사 이미지 검출 및 컨트롤 기능
    - 애니메이션 지원
    - 실시간 로컬 이미지 변경 감지 -> 자동 추가, 삭제
    
- 2020\. 04. 06 ~ 05. 01: 추가기능 도출 및 버그 픽스
    - 쓰레드 관련 버그 대거 수정
    - 포토리스트뷰 삭제 기능 추가
    - 유사 이미지 및 로직 변경
    - 앱 내 광고 삽입
    - 베타 버전 출시
    
- 2020\. 05. 02: 정식 버전 출시

- 2020\. 05. 16: 기능 추가
    - 폴더 사진 개수표시
    - 캘린더 UI 변경
    - 캘린더 태그표시 -> 일정표시
    - 일정 다이얼로그 추가
    
- 2020\. 05. 22: 기능 추가
    - 팝업창 UI 통일
    - 안드로이드 10버전 호환
    - 일정 관리 자동 사진 개수 선택 기능 추가
    - 기타 버그 수정

## 예정

- 유지 보수
- 안드로이드 10 호환 이슈 해결

<br>

# Application 

## Folder List

- 유형 별로 분류한 폴더를 출력한다. 폴더에는 특정 분류에 따라 그에 해당하는 레이블을 출력하고, 폴더 내에 있는 사진의 갯수까지 화면에 출력한다.  
- 사용자는 클릭이벤트를 통해 해당 폴더 내부로 들어갈 수 있다.

### **로컬 폴더 별 분류**

- 로컬 저장소에 있는 사진들을 폴더 별로 분류하여 이미지를 출력한다.  
- 가장 기본적인 폴더 별 분류를 이용하여 사용자는 본인의 사진에 대해 조작을 함에 있어서 손쉽게 다가갈 수 있다.

![image](https://user-images.githubusercontent.com/57826388/82113257-a99d5780-978f-11ea-9681-bda83ba4666d.png)

<br>

### **태그 별 분류**

 - 이미지를 태그별로 분류한다.
 - 앱이 실행된 후 백그라운드로 실행되면서 딥러닝을 통한 이미지에 대한 분석을 하고 특징을 자동으로 도출하여 DB에 입력된다.
 - 특징은 Firebase ML kit image labeling API를 통해 추출해낸다.
 - 추가적으로 추출된 특징이 영어이기 때문에 Firebase ML kit translate API를 통해 서비스 지역 언어로 번역하여 삽입된다.

![image](https://user-images.githubusercontent.com/57826388/82113259-ad30de80-978f-11ea-8621-836fcab22b3c.png)

<br>

### **날짜 별 분류**

- 달력을 년/월 단위로 볼수있게 출력한다.
- 달력의 각각의 일자를 클릭하면 해당 날짜의 이미지를 볼 수 있다.
- 달력에는 해당 날짜의 사진 갯수 뿐만 아니라 일정을 표시한다.
- 일정을 설정하기위한 다이얼로그는 우측 하단 버튼을 누르고 날짜를 클릭하거나, 특정 날짜에 대한 LongClick으로 진입한다.

![image](https://user-images.githubusercontent.com/57826388/82113272-c0dc4500-978f-11ea-97d8-bcce53970423.png)

<br>

### **위치 별 분류**

- 이미지를 위치별로 분류한다.
- 위치는 앱이 실행된 후 백그라운드로 실행되면서 자동으로 로컬 저장소의 사진들의 위치를 추출한다. 그 후, 변환작업을 거쳐 추가 데이터베이스에 삽입되며 사용자의 화면에 출력된다.
- 위치는 시/군/구로 구분되며 시/군/구로 분류가 불가능할 경우, 국가로 구분한다.

![image](https://user-images.githubusercontent.com/57826388/82113308-08fb6780-9790-11ea-8f24-1ba4b19d75de.png)

<br>

## Photo List

- 분류를 통한 UI에서 사용자 클릭 이벤트를 통해 이미지 리스트로 이동한다.
- 본격적으로 분류된 이미지를 출력한다.

<br>

### **이미지 그리드 리스트**

- 분류된 이미지들을 출력한다.
- 출력 과정은 비동기 프로그래밍으로 진행되기 때문에 성능에는 문제가 없다.

![image](https://user-images.githubusercontent.com/57826388/80822554-ec025880-8c15-11ea-96fc-1551cbd7355e.png)

<br>

### **이미지 맵 리스트**

- 구글 맵을 이용하여 이미지의 대한 위치를 마커로 표시한다.
- 마커를 누르게되면 이미지의 태그정보를 말풍선으로 출력하고 하단에 카드뷰 형식으로 이미지에 대한 정보를 출력한다.
- 클러스터링 라이브러리를 통해 마커가 근접하게 여러 개 존재할 경우, 이를 모아준다.
- 하단의 이미지를 클릭하게되면 이미지 출력 뷰로 이동하게 된다.

![KakaoTalk_20200321_222735467](https://user-images.githubusercontent.com/57826388/77233891-f3cde680-6bed-11ea-9ec5-9243a64bc946.jpg)

<br>

## 이미지 출력 뷰

- 이미지 리스트에서 사용자가 특정 이미지를 클릭을 할 경우, 해당 이미지에 해당하는 이미지를 출력한다.
- 이미지를 확대하거나 축소할 수도 있다.
- 좌/우로 스와이프시 이전 또는 이후의 이미지를 출력하는 뷰 페이저 방식으로 구현되었다.
- 이미지를 터치하게되면 상단 이미지 정보와 하단 옵션이 감춰지게되고, 다시 터치하면 나타나게 된다.
- 상단의 별 이미지 클릭을 통해 즐겨찾기 이미지를 지정할 수 있다.

![image](https://user-images.githubusercontent.com/57826388/80965349-55b87780-8e4d-11ea-8b44-7b1d9dd48ed0.png)

![image](https://user-images.githubusercontent.com/57826388/80965361-59e49500-8e4d-11ea-8fea-5cb1bc2882f1.png)

<br>

## 기타 기능

### **즐겨찾기**

- 메인 화면에서 우측 상단 옵션을 통해 즐겨찾기 뷰로 이동할 수 있다.
- 즐겨찾기가 되어있는 이미지를 출력해준다.

![image](https://user-images.githubusercontent.com/57826388/80965372-5e10b280-8e4d-11ea-98ec-c56c243c21ae.png)

<br>

### **이미지 삭제**

- 뷰페이저의 하단 옵션에서 삭제를 클릭하면 이미지를 삭제할 수 있다.
- 삭제는 로컬 저장소의 해당 이미지, 해당 이미지의 ID가 포함된 DB의 모든 데이터가 지워지게 된다.

![image](https://user-images.githubusercontent.com/57826388/80965688-f313ab80-8e4d-11ea-96f6-cd736837ae7f.png)

<br>

### **리스트 이미지 삭제**

- 포토 리스트에서 임의로 체크박스를 두어 사진들을 선택하여 삭제할 수 있다.
- 상단의 휴지통 모양을 누르면 삭제를 제어하는 버튼들이 생긴다. 이를 통해 지우고자 하는 이미지들을 체크하여 삭제할 수 있다.

![image](https://user-images.githubusercontent.com/57826388/80822590-03d9dc80-8c16-11ea-8d0d-57c85f04a62a.png)

![image](https://user-images.githubusercontent.com/57826388/80822599-076d6380-8c16-11ea-9acc-3c14944aa281.png)

<br>

### **태그 입력, 수정, 삭제**

- 사용자는 태그를 최대 5개까지 관리 가능하다.
- 태그 입력 버튼을 누르면 자동으로 이미지의 태그들로 다이얼로그를 채운다.
- 거기서 태그를 추가하거나 지우는 행위를 통해 태그를 직접 조작할 수 있다.

![image](https://user-images.githubusercontent.com/57826388/80965379-61a43980-8e4d-11ea-83b4-3fbfd52511a1.png)

<br>

### **SNS 공유**

- 다음 공유 버튼을 통하여 SNS로 해당 이미지를 공유할 수 있다.

![image](https://user-images.githubusercontent.com/57826388/82754768-456f3900-9e0a-11ea-83c0-4be2329d8861.png)

![image](https://user-images.githubusercontent.com/57826388/82754775-4bfdb080-9e0a-11ea-8d14-085e2b029e71.png)

<br>

### **이미지 검색**

- 사용자는 원하는 이미지를 직접 검색할 수 있다.
- 유형은 이름, 위치, 태그, 날짜로 이루어지며 검색을 하면 키워드가 포함된 모든 이미지를 고유한 이름으로 출력한다.
- 검색화면은 메인화면 상단의 돋보기 모양 버튼을 통해 이동할 수 있다.

![image](https://user-images.githubusercontent.com/57826388/82113330-2f210780-9790-11ea-8cb9-fd378efaa834.png)


<br>

### **카메라**

- 사용자는 메인화면 상단 툴바에 카메라 버튼을 통해 카메라 캡처를 할 수 있다.
- 캡처한 이미지는 `Pictures/Wimmy` 폴더에 저장된다.

![image](https://user-images.githubusercontent.com/57826388/82113257-a99d5780-978f-11ea-9681-bda83ba4666d.png)

<br>

### **로딩 화면**

- 본 서비스를 이용하기 위해서는 일부 권한에 허가를 해야하고 특정 API 모델을 사용자의 기기에 설치해야 한다. 이 과정을 로딩 화면에서 제어한다.

![KakaoTalk_20200321_222912042](https://user-images.githubusercontent.com/57826388/77233906-f92b3100-6bed-11ea-8995-130f9561a484.jpg)

<br>

### **유사 이미지 검색**

- 사용자가 연속사진을 촬영하거나 여러 사진을 한꺼번에 수용하는 경우, 마음에 드는 몇 개의 사진을 제외하고는 나머지 사진들을 일일이 지워야 하는 경우가 있다.
- 사용자는 특정 이미지에 대해 유사 이미지를 검색하고 이를 추가 삭제할 수 있다.
- 유사 이미지는 날짜와 위치의 연관성으로 구분한다.

![image](https://user-images.githubusercontent.com/57826388/80075077-16ed0c80-8585-11ea-83fe-013fe7747266.png)

![image](https://user-images.githubusercontent.com/57826388/80075092-1c4a5700-8585-11ea-87e6-a431b02acf87.png)

<br>

### **일정 관리**

- 달력에서 사용자는 우측 하단의 버튼이나, 특정 날짜에 대한 LongClick을 통해 일정 관리 다이얼로그를 호출할 수 있다.
- 다이얼로그에서는 해당 날짜의 사진들을 둘러보며 주제와 메모를 입력할 수 있다.
- 입력된 주제는 해당 달력에 표시된다.

![image](https://user-images.githubusercontent.com/57826388/82644034-a270c080-9c4b-11ea-8229-4f4ab7912190.png)

![image](https://user-images.githubusercontent.com/57826388/82113351-61326980-9790-11ea-8ef9-1607e53b7a51.png)

![image](https://user-images.githubusercontent.com/57826388/82754794-659ef800-9e0a-11ea-9b5b-e3ec4634a2e1.png)

<br>

### **사진/폴더 개수 관리**

- 메뉴에서 사진 및 폴더의 표시 개수를 설정할 수 있다.

![image](https://user-images.githubusercontent.com/57826388/82754828-9848f080-9e0a-11ea-8b7c-01f4df451734.png)

![image](https://user-images.githubusercontent.com/57826388/82754821-8e26f200-9e0a-11ea-8d7d-a8eb2f42ff12.png)

![image](https://user-images.githubusercontent.com/57826388/82754825-92530f80-9e0a-11ea-921f-e1084f33eebb.png)

<br>

## Others

### Custom Date picker Dialog
### 최상단, 최하단 버튼
### 상단 툴바 숨기기
### 프래그먼트 생애주기 백 스택 관리
### GestureDetector
### 위치별 보기 옵션
### 애니메이션
### ...