// 실시간 아이디 중복체크
var $id = $("#id");
// 아이디 정규식
$id.on("keyup", function() { // 키보드에서 손을 땠을 때 실행
    var regExp = /^[a-z]+[a-z0-9]{5,15}$/g;

    if (!regExp.test($id.val())) { // id 가 공백인 경우 체크
        idchk = false;
        $id.html("<span id='check'>사용할 수 없는 아이디입니다.</span>");
        $("#check").css({
            "color" : "#FA3E3E",
            "font-weight" : "bold",
            "font-size" : "10px"
        })
    } else { // 공백아니면 중복체크
        $.ajax({
            type : "POST", // http 방식
            url : "/login/checkid", // ajax 통신 url
            data : { // ajax 내용 => 파라미터 : 값 이라고 생각해도 무방
                "type" : "user",
                "id" : $id.val()
            },
            success : function(data) {
                if (data == 1) { // 1이면 중복
                    idchk = false;
                    $id.html("<span id='check'>이미 존재하는 아이디입니다</span>")
                    $("#check").css({
                        "color" : "#FA3E3E",
                        "font-weight" : "bold",
                        "font-size" : "10px"

                    })
                    //console.log("중복아이디");
                } else { // 아니면 중복아님
                    idchk = true;
                    $id.html("<span id='check'>사용가능한 아이디입니다</span>")

                    $("#check").css({
                        "color" : "#0D6EFD",
                        "font-weight" : "bold",
                        "font-size" : "10px"

                    })
                    //console.log("중복아닌 아이디");
                }
            }
        })
    }
});

// 비밀번호 클릭 시 문구창
document.getElementById('user-pwd1').addEventListener('focus', function() {
    document.querySelector('.password-info').style.display = 'block';
});

// 연락처 숫자 값 + 글자 수 제한
function maxLengthCheck(object) {
    if (object.value.length > object.maxLength) {
        object.value = object.value.slice(0, object.maxLength);
    }
}


// 이메일 자동 완성
function autoEmail(a, b) {
    var mailId = b.split('@');                                                                   // 메일계정의 ID만 받아와서 처리하기 위함
    var mailList = ['naver.com', 'gmail.com', 'daum.net', 'hanmail.net', 'nate.com'];    // 메일목록
    var availableCity = new Array;                                                        // 자동완성 키워드 리스트
    for (var i = 0; i < mailList.length; i++) {
        availableCity.push(mailId[0] + '@' + mailList[i]);                                       // 입력되는 텍스트와 메일목록을 조합
    }
    $("#" + a).autocomplete({
        source: availableCity,                                                                   // jQuery 자동완성에 목록을 넣어줌
        focus: function (event, ui) {
            return false;
        }
    });
}

// // 체크 박수 일괄 선택 및 필수 체크 시 회원가입 버튼 활성화
// 'use strict';   // 엄격모드 활성화
//
// const form = document.querySelector('.check-agree');
// const checkAll = document.querySelector('.terms+check_all input');
// const checkBoxes = document.querySelectorAll('.input_check input');
// const submitButton = document.querySelector('button');
//
// const agreements = {
//     police: false,
//     private: false,
//     promotion: false,
// };
//
// // 폼이 제출되면 브라우저는 폼 데이터를 서버로 전송하고 새로고침하기 때문에 새로고침되지 않고 기본 동작을 중단함
// // e.preventDefault() : 이벤트 기본 동작 중단
// form.addEventListener('submit', (e) => e.preventDefault());
//
// // toggleCheckbox : 체크박스의 input 이벤트가 발생했을 때 호출되는 이벤트 핸들러 함수
// checkBoxes.forEach((item) => item.addEventListener('input', toggleCheckbox));
//
// function toggleCheckbox(e) {
//     const {checked, id} = e.target;
//     agreements[id] = checked;
//     this.parentNode.classList.toggle('active');
//     checkAllStatus();
//     toggleSubmitButton();
// }

// function checkAllStatus() {
//     const {police, private, promotion} agreements;
//     if (police && private && promotion) {
//         checkAll.checked = true;
//     } else {
//         checkAll.checked = false;
//     }
// }



