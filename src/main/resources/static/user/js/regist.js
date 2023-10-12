window.onload = function() {

    if (document.getElementById("regist")) {
        const $regist = document.getElementById("regist");
        $regist.onclick = function () {
            location.href = "/user/regist";
        }
    }

    // 아이디 중복 확인
    if (document.getElementById("idCheck")) {

        const $duplication = document.getElementById("idCheck");

        $duplication.onclick = function () {

            let memberId = document.getElementById("id").value.trim();

            fetch("/user/idCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({memberId: memberId})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));

        }

    }

    // HTML 로드된 후에 스크립트 실행
    document.addEventListener('DOMContentLoaded', function () {

        // 비밀번호 정규식에 맞춰 작성
        document.getElementById('pwd1').addEventListener('blur', function () {
            const password = this.value;
            const regex = /^(?!.*(.)\1\1)(?!((?:[A-Za-z]+)|(?:[~`!@#$^()*_\-={}[]|;:<>,.?\/]+)|(?:[0-9]+))$)[A-Za-z\d~`!@#$^()*_\-={}[]|;:<>,.?\/]{10,}$/;
            const isValid = regex.test(password);
            const messageElement = document.getElementById('pwdError');

            if (password.length >= 10 && isValid) {
                messageElement.textContent = "";
            } else if (password.length > 0) {
                messageElement.textContent = "비밀번호가 형식에 맞지 않습니다.";
            }
        });

        // 비밀번호 클릭 시 비밀번호 형식 안내 문구창
        const pwd1Element = document.getElementById("pwd1");
        const pwdMessageElement = document.getElementById("pwdMessage");

        pwd1Element.addEventListener("click", function (event) {
            // event.stopPropagation();
            pwdMessageElement.style.display = "block";
        });

        document.addEventListener("click", function (event) {
            if (event.target !== pwd1Element && event.target !== pwdMessageElement) {
                pwdMessageElement.style.display = "none";
            }
        });

        document.addEventListener("keydown", function (event) {
            if (event.key === "Tab") {
                pwdMessageElement.style.display = "none";
            }
        });

        // 비밀번호 일치 확인
        document.getElementById('pwd1').addEventListener('input', checkPasswords);
        document.getElementById('pwd2').addEventListener('input', checkPasswords);

        function checkPasswords() {
            const password1 = document.getElementById('pwd1').value;
            const password2 = document.getElementById('pwd2').value;
            const messageElement = document.getElementById('pwdError2')

            if (password1 === password2) {
                messageElement.textContent = "";
            } else {
                messageElement.textContent = "비밀번호가 일치하지 않습니다."
            }

        }


        // 연락처 숫자 값 + 글자 수 제한
        function maxLengthCheck(object) {
            if (object.value.length > object.maxLength) {
                object.value = object.value.slice(0, object.maxLength);
            }
        }


        // 이메일 자동 완성
        function autoEmail(a, b) {
            const mailId = b.split('@');                                                                   // 메일계정의 ID만 받아와서 처리하기 위함
            const mailList = ['naver.com', 'gmail.com', 'daum.net', 'hanmail.net', 'nate.com'];    // 메일목록
            const availableCity = new Array;                                                        // 자동완성 키워드 리스트
            for (var i = 0; i < mailList.length; i++) {
                availableCity.push(mailId[0] + '@' + mailList[i]);                                         // 입력되는 텍스트와 메일목록을 조합
            }
            $("#" + a).autocomplete({
                source: availableCity,                                                                     // jQuery 자동완성에 목록을 넣어줌
                focus: function (event, ui) {
                    return false;
                }
            });
        }


        // 변수 선언 : DOM 요소 참조하기 위해(HTML에 정의된 요소를 자바스크립트로 조작하기 위해)
        var checkAllCheckbox = document.getElementById('checkAll');
        var requiredCheck = document.getElementsByClassName('requiredCheck');
        var optionalCheck = document.getElementsByClassName('optionalCheck');
        var submitBtn = document.getElementById('joinBtn');

        // 필수 체크 박스 모두 체크 시 회원가입 버튼 활성화
        function submitButtonActivate() {

            var allRequiredChecked = true;

            for (var i = 0; i < requiredCheck.length; i++) {

                if (!requiredCheck[i].checked) {
                    allRequiredChecked = false;
                    break;
                }

            }

            submitBtn.disabled = !allRequiredChecked;

        }

        // 필수 체크 박스와 선택 체크 박스가 모두 체크되어 있으면 checkAllCheckbox 또한 체크하고 아닌 경우 해제
        function updateCheckAllCheckbox() {

            var allChecked = true;

            for (var i = 0; i < requiredCheck.length; i++) {
                if (!requiredCheck[i].checked) {
                    allChecked = false;
                    break;
                }
            }

            for (var i = 0; i < optionalCheck.length; i++) {
                if (!optionalCheck[i].checked) {
                    allChecked = false;
                    break;
                }
            }

            checkAllCheckbox.checked = allChecked;
        }

        // 필수 체크 박스와 선택 체크 박스를 일괄 체크하고 회원가입 버튼 활성화
        checkAllCheckbox.addEventListener('change', function () {

            for (var i = 0; i < requiredCheck.length; i++) {
                requiredCheck[i].checked = this.checked;
            }

            for (var i = 0; i < optionalCheck.length; i++) {
                optionalCheck[i].checked = this.checked;
            }

            submitButtonActivate();

        })

        // 체크 박스의 상태에 따라 updateCheckALlCheckbox() 함수가 호출되어 checkAllCheckbox의 상태를 업데이트 후 submitButtonActivate() 함수 호출해 회원가입 버튼 활성화 여부 결정
        for (var i = 0; i < requiredCheck.length; i++) {
            requiredCheck[i].addEventListener('change', function () {
                updateCheckAllCheckbox();
                submitButtonActivate();
            })
        }

        for (var i = 0; i < optionalCheck.length; i++) {
            optionalCheck[i].addEventListener('change', function () {
                updateCheckAllCheckbox();
                submitButtonActivate();
            });
        }

    });

}

