/**
 * 
 */
const tagListButtons = document.querySelectorAll('.taglist');
    tagListButtons.forEach((button) => {
        if (button.innerText.trim() === '') {
            button.style.display = 'none';
            const liElement = button.parentElement;
            if (liElement) {
                liElement.style.display = 'none';
            }
        }
    });

    const tagBtns = document.querySelectorAll('.tagBtn');
    tagBtns.forEach((btn) => {
        if (btn.innerText.trim() === '') {
            btn.style.display = 'none';
        }
    });

    // 	deleteCookie("selSeries");
    deleteAllCookies();
    //외부 html 붙히기
    // 	$(function() {
    // 	  $("#series").load("/kdy/series.html");
    // 	  $("#introduce").load("/introduce/body.html");
    // 	});
    
   

    function showall() {
        var postAreas = document.getElementsByClassName("postArea");
        for (var i = 0; i < postAreas.length; i++) {
            postAreas[i].style.display = "block";
        }
    }

    function goPost(id) {		//게시물 상세 페이지로 게시물 아이디 넘기기
// 		alert("개굴게시물 : "+id); 
        location.href = '/goPost/' + id;///login
    }

    function deleteAllCookies() {
        var cookies = document.cookie.split(";");

        for (var i = 0; i < cookies.length; i++) {
            var cookie = cookies[i];
            var eqPos = cookie.indexOf("=");
            var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
            document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/";
        }
    }

    function search(event) {
        if (event.keyCode === 13) {
            // 엔터 키를 눌렀을 때 처리할 코드
            var inputSearch = document.getElementById("inputSearch").value;
            ajaxSearch(inputSearch);

        }
    }

    function ajaxSearch(inputSearch) {
        if (inputSearch.trim() === '') {
            alert("검색어를 입력해주세요!!");
            return;
        }

        // ajax통신
        // 검색해서 검색어를 포함한 게시물들의 아이디를 긴 스트링으로 받기
        // 스트링으로 받은 후 div아이디로 받은 아이디만 보이게 display설정하기

        let xhr = new XMLHttpRequest();
        xhr.open("POST", "search?inputSearch=" + inputSearch, true);
        xhr.send();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                var searchString = xhr.responseText.trim();
                var searchArr = searchString.split(",");
                var divs = document.getElementsByClassName("postArea");
                for (var i = 0; i < divs.length; i++) {
                    var id = divs[i].id;
                    id = id.substring(7); //뒤의 숫자아이디만 가져오기
                    if (searchArr.includes(id)) {
                        var newdiv = document.getElementById("boardId" + id); //boardId1
                        newdiv.style.display = "block";
                    } else {
                        var newdiv = document.getElementById("boardId" + id);
                        newdiv.style.display = "none";
                    }
                }

            }
        }
    }

    function showForm() {
        // const btnShowForm = document.getElementById("btn-show-form");
        // const formContainer1 = document.getElementById("form-container1"); //글작성하기 폼
        // const anyimg = document.getElementById("anyimg");
        // const anytext = document.getElementById("anytext");
        // btnShowForm.style.display = "none";
        // anyimg.style.display = "none";
        // anytext.style.display = "none";
        // formContainer1.style.display="flex";

        const formContainer0 = document.getElementById("form-container0");
        const formContainer2 = document.getElementById("form-container2");
        formContainer0.style.display = "none";
        formContainer2.style.display = "flex";


    }

    function createSaveForm() {
        const introform1 = document.getElementById("introform1");
        const introText = document.getElementById("intro1");
        const introBtn = document.getElementById("introEditBtn1");

        if (introBtn.innerText === "수정하기") {
            introText.removeAttribute("readonly");
            introBtn.innerText = "저장하기";
        } else {
            introText.setAttribute("readonly", "readonly");
            introBtn.innerText = "수정하기";
            introform1.submit();
        }
    }

    autosize(document.getElementById('intro1'));
    autosize(document.getElementById('intro2'));