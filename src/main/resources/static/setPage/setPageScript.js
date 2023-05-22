// th:inline="javascript"

document.addEventListener('DOMContentLoaded', function () {
    // DOM 로딩이 완료된 후 실행할 자바스크립트 코드 작성
    // ===================== 프로필 이미지 업로드, 삭제 기능 =====================
    window.addEventListener('DOMContentLoaded', (event) => {
        const uploadButton = document.getElementById('UPB');
        const removeButton = document.getElementById('RMB');
        const profileImage = document.getElementById('profile-image');

        uploadButton.addEventListener('click', () => {
            // 이미지 업로드 버튼 클릭 시, 파일을 선택 창 띄우기
            const input = document.createElement('input');
            input.type = 'file';
            input.accept = 'image/*';
            input.addEventListener('change', (event) => {
                const file = event.target.files[0];
                const reader = new FileReader();

                reader.addEventListener('load', (event) => {
                    // 업로드된 파일로 프로필 이미지 소스를 업데이트
                    profileImage.src = event.target.result;
                });
                reader.readAsDataURL(file);
            });
            input.click();
        });

        removeButton.addEventListener('click', () => {
            // 이미지 제거 버튼 클릭 시, 업로드 된 소스 삭제 (디폴트 이미지 설정해둠)
            profileImage.src = 'img/defaultImage.png';
        });
    });


    // ===================== info-area 수정 기능 (메인 프로필) =====================

    // 수정 버튼 클릭 이벤트 처리
    document.getElementById('editButton_Mp').addEventListener('click', function () {
        // 디폴트값 숨기고, 숨겨진 수정 폼 보이게 하기
        document.querySelector('#defInfo').style.display = 'none';
        document.querySelector('#defInfo_editForm').style.display = 'block';
    });

    // 기존 데이터 가져오기
    var vUsername = document.querySelector('.vUsername');
    var vUserBio = document.querySelector('.vUserBio');

    // 가져온 데이터를 폼에 설정하기
    document.querySelector('.vName_e').value = vUsername.innerText;
    document.querySelector('.vBio_e').value = vUserBio.innerText;

    // 저장 버튼 클릭 이벤트 처리
    document.querySelector('#save-btn-info').addEventListener('click', function (event) {
        event.preventDefault(); // 폼 제출 막기

        // 입력한 값을 가져와서 페이지에 반영하기
        var updateNameData = document.querySelector('.vName_e').value;
        vUsername.innerText = updateNameData;

        var updateBioData = document.querySelector('.vBio_e').value;
        vUserBio.innerText = updateBioData;

        // 수정폼 숨기기, 디폴트값 다시 띄우기
        document.querySelector('#defInfo').style.display = 'block';
        document.querySelector('#defInfo_editForm').style.display = 'none';

        document.querySelector('#editForm').submit();
    });

    // ===================== 벨로그 정보 수정 기능 (위와 같은 기능이니 주석 패스할게요~) =====================
    document.getElementById('editButton_vN').addEventListener('click', function () {
        document.querySelector('#vPageName').style.display = 'none';
        document.querySelector('#vPageName_editForm').style.display = 'block';
    });

    var vPageNameT = document.querySelector('#vPageNameT');
    var vPageNameT_e = document.querySelector('#vPageNameT_e');
    var vPageNameData = vPageNameT.innerText;
    vPageNameT_e.value = vPageNameData;

    document.querySelector('#save-btn-PageName').addEventListener('click', function (event) {
        event.preventDefault();

        var updatePageData = vPageNameT_e.value;
        vPageNameT.innerText = updatePageData;

        document.querySelector('#vPageName').style.display = 'flex';
        document.querySelector('#vPageName_editForm').style.display = 'none';

        document.querySelector('#editForm2').submit();
    });

    // ===================== 소셜 정보 수정 기능 =====================
    document.getElementById('editButton_sns').addEventListener('click', function () {
        document.querySelector('#snsInfo').style.display = 'none';
        document.querySelector('#snsInfo_e').style.display = 'block';
    });

    var snsEmail = document.querySelector('#snsEmail');
    var snsGithub = document.querySelector('#snsGithub');
    var snsTwitter = document.querySelector('#snsTwitter');
    var snsFacebook = document.querySelector('#snsFacebook');
    var snsHome = document.querySelector('#snsHome');

    var snsEmail_e = document.querySelector('#snsEmail_e');
    var snsGithub_e = document.querySelector('#snsGithub_e');
    var snsTwitter_e = document.querySelector('#snsTwitter_e');
    var snsFacebook_e = document.querySelector('#snsFacebook_e');
    var snsHome_e = document.querySelector('#snsHome_e');

    var snsEmailData = snsEmail.innerText;
    var snsGithubData = snsGithub.innerText;
    var snsTwitterData = snsTwitter.innerText;
    var snsFacebookData = snsFacebook.innerText;
    var snsHomeData = snsHome.innerText;

    snsEmail_e.value = snsEmailData;
    snsGithub_e.value = snsGithubData;
    snsTwitter_e.value = snsTwitterData;
    snsFacebook_e.value = snsFacebookData;
    snsHome_e.value = snsHomeData;

    document.querySelector('#save-btn-sns').addEventListener('click', function (event) {
        event.preventDefault();

        var updateEmailData = snsEmail_e.value;
        var updateGithubData = snsGithub_e.value;
        var updateTwitterData = snsTwitter_e.value;
        var updateFacebookData = snsFacebook_e.value;
        var updateHomeData = snsHome_e.value;

        snsEmail.innerText = updateEmailData;
        snsGithub.innerText = updateGithubData;
        snsTwitter.innerText = updateTwitterData;
        snsFacebook.innerText = updateFacebookData;
        snsHome.innerText = updateHomeData;

        document.querySelector('#snsInfo').style.display = 'flex';
        document.querySelector('#snsInfo_e').style.display = 'none';

        document.querySelector('#editForm3').submit();
    });

    // 회원 탈퇴 경고창 (못 생긴 거)

    // window.addEventListener('DOMContentLoaded', (event) => {
    //   const confirmDeleteAccount = document.getElementById('deleteAccount');
    //   confirmDeleteAccount.addEventListener('click', () => {
    //     const confirmation = confirm('정말 탈퇴하시겠습니까?');
    //     if (confirmation) {
    //       console.log('DeleteAC confirmed');
    //     } else {
    //       console.log('DeleteAC canceled');
    //     }
    //   });
    // });

    // ===================== 회원 탈퇴 경고창 (더 이쁜거로 만듦)  =====================
    window.addEventListener('DOMContentLoaded', (event) => {
        const deleteAccountButton = document.getElementById('deleteAccount');
        const deleteAcFrame = document.querySelector('.deleteAcFrame');
        const cancelBtn = document.querySelector('.cancel_b');
        const confirmBtn = document.querySelector('.ok_b');

        deleteAccountButton.addEventListener('click', () => {
            // deleteAccount 버튼이 클릭시 deleteAcFrame 등장
            deleteAcFrame.style.display = 'block';
        });

        cancelBtn.addEventListener('click', () => {
            // 취소 버튼이 클릭되었을 때 deleteAcFrame 숨김
            deleteAcFrame.style.display = 'none';
        });

        confirmBtn.addEventListener('click', () => {
            console.log('회원 탈퇴 승인');
            // const memberId = '[[${memberId}]]';
            // location.href = "/members/delete/" + memberId;
            location.href = "/members/delete/"
            location.href = "/gologin";
        });

        // confirmBtn.addEventListener('click', () => {
        //   console.log('탈퇴가 확인되었습니다.');
        //   location.href="/members/delete/[[${memberId}]]";
        //   // 여기에 실제 탈퇴 작업을 수행하는 코드 추가
        // });
    });

    // ===================== 토글 스위치 기능 =====================
    const toggleList = document.querySelectorAll(".toggleSwitch");

    toggleList.forEach(($toggle) => {
        $toggle.onclick = () => {
            $toggle.classList.toggle('active');
        }
    });

});

