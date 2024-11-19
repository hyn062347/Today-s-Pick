<script>
    import { onMount } from "svelte";

    // 슬라이드 데이터
    let slides = [
        { image: "/img/slide1.png", text: "오늘의 추천1" },
        { image: "/img/slide2.png", text: "오늘의 추천2" },
        { image: "/img/slide3.png", text: "오늘의 추천3" }
    ];

    let currentIndex = 0; // 현재 슬라이드 인덱스

    // 슬라이드 변경 함수
    function nextSlide() {
        currentIndex = (currentIndex + 1) % slides.length;
    }

    // 컴포넌트 마운트 시 슬라이드 자동 전환 설정
    onMount(() => {
        const interval = setInterval(nextSlide, 3000); // 3초마다 슬라이드 변경
        return () => clearInterval(interval); // 언마운트 시 정리
    });
</script>

<div class="slider">
    {#each slides as slide, index}
        <div class="slide {index === currentIndex ? 'active' : ''}">
            <img src={slide.image} alt="슬라이드 이미지" />
            <div class="slide-text">{slide.text}</div>
        </div>
    {/each}
</div>

<style>
    .slider {
        position: relative;
        width: 100%;
        height: 400px;
        overflow: hidden;
        background-color: #e0f7fa;
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 20px;
    }
    .slide {
        position: absolute;
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        opacity: 0;
        transition: opacity 0.5s ease-in-out;
    }
    .slide.active {
        opacity: 1;
    }
    .slide img {
        width: 100%;
        height: auto;
        object-fit: cover;
    }
    .slide-text {
        position: absolute;
        bottom: 20px;
        background: rgba(0, 0, 0, 0.5);
        color: white;
        padding: 10px 20px;
        border-radius: 10px;
    }
</style>
