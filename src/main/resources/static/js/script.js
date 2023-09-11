window.onload = function () {

  // const positionCg = document.querySelectorAll(".position_category > li > a");
  // const positionMenu = document.querySelectorAll(".position_menu > li");

  // positionCg.forEach((tab, idex) => {
  //   tab.addEventListener("click", function () {
  //     positionMenu.forEach((inner) => {
  //       inner.classList.remove("position_active");
  //     });
  //     positionCg.forEach((item) => {
  //       item.classList.remove("position_active");
  //     });
  //     positionCg[idex].classList.add("position_active");
  //     positionMenu[idex].classList.add("position_active");
  //   });
  // });
  // 직무 카테고리 탭
  const myButton = document.getElementById('myButton');
  let isActive = false;

  function toggleButton(positionId) {
    isActive = !isActive; // 버튼의 활성 상태를 토글합니다.

    if (isActive) {
      myButton.classList.add('active'); // 버튼에 'active' 클래스 추가
      handlePositionClick(positionId); // 원하는 동작 실행
    } else {
      myButton.classList.remove('active'); // 'active' 클래스 제거
      // 누른 상태에서 버튼을 다시 클릭했을 때의 동작을 여기에 추가
    }
  }




  const tabButtons = document.querySelectorAll('.tab_menu li a');
  const tabContents = document.querySelectorAll('.tab-content');

  tabButtons.forEach(button => {
    button.addEventListener('click', function (event) {
      event.preventDefault();

      tabContents.forEach(content => {
        content.style.display = 'none';
      });

      tabButtons.forEach(btn => {
        btn.classList.remove('tab_active');
      });

      const tabId = button.getAttribute('data-tab');
      document.getElementById(tabId).style.display = 'block';

      button.classList.add('tab_active');
    });
  });
  // 마이페이지 탭


};
