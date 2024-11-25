<script>
    export let onClose;
</script>


<ul class="option">
    <li><a href="/mypage">마이페이지</a></li>
    <li><a href="/recipe">내 레시피</a></li>
    <li><a href="/history">히스토리</a></li>
    <li><a href="/">로그아웃</a></li>
</ul>


<style>
    .option {
        position: absolute;
        top: 60px; /* 프로필 이미지 아래에 드롭다운 위치 */
        right: 0;
        background: white;
        border: 1px solid #ddd;
        border-radius: 8px;
        list-style: none;
        padding: 0;
        margin: 0;
        width: 150px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        z-index: 1000; /* 다른 요소보다 위로 표시되도록 높은 값 설정 */
    }

    .option li {
        padding: 10px;
        text-align: center;
        border-bottom: 1px solid #eee;
    }

    .option li:last-child {
        border-bottom: none;
    }

    .option li a {
        text-decoration: none;
        color: black;
        display: block;
    }

    .option li a:hover {
        background: #f0f0f0;
    }
</style>