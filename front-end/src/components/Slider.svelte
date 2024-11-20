<script>
    import { onMount } from "svelte";

    // 슬라이드 데이터
    let slides = [
        { image: "/img/slide1.png", text: "오늘의 추천1" },
        { image: "/img/slide2.png", text: "오늘의 추천2" },
        { image: "/img/slide3.png", text: "오늘의 추천3" }
    ];

    let currentIndex = 0; // 현재 슬라이드 인덱스
    let transitioning = false; // 슬라이드 전환 중인지 확인

    // 다음 슬라이드로 이동
    function nextSlide() {
        if (transitioning) return;
        transitioning = true;

        currentIndex = (currentIndex + 1) % slides.length;

        // 500ms 후 전환 가능 (애니메이션과 동일 시간)
        setTimeout(() => (transitioning = false), 500);
    }

    // 이전 슬라이드로 이동
    function previousSlide() {
        if (transitioning) return;
        transitioning = true;

        currentIndex = (currentIndex - 1 + slides.length) % slides.length;

        // 500ms 후 전환 가능 (애니메이션과 동일 시간)
        setTimeout(() => (transitioning = false), 500);
    }

    // 슬라이드 자동 전환
    onMount(() => {
        const interval = setInterval(nextSlide, 3000); // 3초마다 슬라이드 변경
        return () => clearInterval(interval); // 컴포넌트 언마운트 시 정리
    });
</script>

<div class="slider">
    <!-- 슬라이드 컨테이너 -->
    <div class="slides" style="transform: translateX(-{currentIndex * 100}%);">
        {#each slides as slide}
            <div class="slide">
                <img src={slide.image} alt="슬라이드 이미지" />
                <!-- <div class="slide-text">{slide.text}</div> -->
            </div>
        {/each}
    </div>

    <!-- 좌우 버튼 -->
    <button class="prev" on:click={previousSlide}>&lt;</button>
    <button class="next" on:click={nextSlide}>&gt;</button>
</div>

<style>
    /* 슬라이더 영역 스타일 */
    .slider {
        position: relative;
        width: 100%;
        height: 360px;
        overflow: hidden;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    /* 슬라이드 전체를 감싸는 컨테이너 */
    .slides {
        position: absolute;
        display: flex;
        transition: transform 0.5s ease-in-out; /* 부드러운 이동 애니메이션 */
        width: 100%;
        height: 100%;
    }

    /* 개별 슬라이드 스타일 */
    .slide {
        flex: 0 0 100%; /* 슬라이드가 100% 너비를 차지 */
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }

    /* 슬라이드 이미지 */
    .slide img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    /* 슬라이드 텍스트 */
    .slide-text {
        position: absolute;
        bottom: 20px;
        background: rgba(0, 0, 0, 0.5);
        color: white;
        padding: 10px 20px;
        border-radius: 10px;
    }

    /* 좌우 버튼 스타일 */
    .prev, .next {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        background: rgba(0, 0, 0, 0.5);
        color: white;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        z-index: 10;
        font-size: 20px;
    }

    .prev {
        left: 10px;
    }

    .next {
        right: 10px;
    }

    .prev:hover, .next:hover {
        background: rgba(0, 0, 0, 0.8);
    }
</style>
