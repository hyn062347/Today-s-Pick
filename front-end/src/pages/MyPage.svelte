<script>
    let userData = {        // 유저 프로필 (임시)
        image: 'https://via.placeholder.com/100',  // 프로필 이미지
        uid: 'rang',        // 아이디 
        password: '1234',    // 비밀번호
        email: 'rang@gmail.com',      // 이메일
        uname: '고해랑',       // 닉네임
        preference: []      // 선호 음식 (체크리스트)
    };

    const Options = ["곡류, 서류 제품", "과일류", "구이류",
                     "국 및 탕류", "김치류", "나물·숙채류",
                     "두류, 견과 및 종실류", "면 및 만두류",
                     "밥류", "볶음류", "빵 및 과자류",
                     "생채·무침류", "수·조·어·육류", "유제품류 및 빙과류",
                     "음료 및 차류", "장류, 양념류", "장아찌·절임류",
                     "전·적 및 부침류", "젓갈류", "조림류", "죽 및 스프류",
                     "찌개 및 전골류", "찜류", "튀김류"
                    ];

    function checkOption(option) {
        if (userData.preference.includes(option)) {
            userData.preference = userData.preference.filter((item) => item !== option);
        } else {
            userData.preference = [...userData.preference, option];
        }
    }
    async function setPreference(){
        try {
            const response = await fetch(
                "http://113.198.238.115:8080/api/preference/set",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(userData),
                    credentials: "include",
                },
            );

            if (!response.ok) {
                throw new Error(`서버오류: ${response.status}`);
            }

            const result = await response.json();
            console.log("서버 응답", result);
            userData.preference=result;

        } catch (error) {
            console.error(`데이터 전송 오류`, error);
        }
        
    }
    async function setUser(){
        try {
            userData.uid=sessionStorage.getItem("idkey");
            const response = await fetch(
                "http://113.198.238.115:8080/api/account/setuser",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(userData),
                    credentials: "include",
                },
            );

            if (!response.ok) {
                throw new Error(`서버오류: ${response.status}`);
            }

            const result = await response.json();
            console.log("서버 응답", result);

            setPreference();

        } catch (error) {
            console.error(`데이터 전송 오류`, error);
        }
    }

    async function getPreference(){
        try {
            const response = await fetch(
                "http://113.198.238.115:8080/api/preference/all",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(userData),
                    credentials: "include",
                },
            );

            if (!response.ok) {
                throw new Error(`서버오류: ${response.status}`);
            }

            const result = await response.json();
            console.log("서버 응답", result);
            userData.preference=result;

        } catch (error) {
            console.error(`데이터 전송 오류`, error);
        }
    }

    async function getUserInfo() {
        try {
            userData.uid=sessionStorage.getItem("idkey");
            const response = await fetch(
                "http://113.198.238.115:8080/api/account/getuser",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(userData),
                    credentials: "include",
                },
            );

            if (!response.ok) {
                throw new Error(`서버오류: ${response.status}`);
            }

            const result = await response.json();
            console.log("서버 응답", result);
            userData.email=result.email;
            userData.uname=result.uname;
            userData.image=result.uimg_src+result.uimg_name;

            getPreference();

        } catch (error) {
            
        }
    }

    getUserInfo();
</script>

<main class="setCenter">
    <div class="form-box">
        <form class="form" on:submit|preventDefault={() => console.log(userData)}>
            <span class="title">프로필</span>
            <div>
                {#if userData.image}
                    <img src={userData.image} alt="Image" class="profileImage"/>
                {:else}
                    <img src="/img/noImg.png" class="profileImage"/>
                {/if}
            </div>
            
            <div class="form-container">
                <input type="ID" class="input nonclick" bind:value={userData.uid} placeholder="ID" />
                <input type="text" class="input" bind:value={userData.uname} placeholder="Name"/>
                <input type="email" class="input last" bind:value={userData.email} placeholder="Email"/>
                <fieldset>
                    <legend>선호 음식 종류</legend>
                    <div class="preferences">
                        {#each Options as option}
                            <label class="checkbox">
                                <span 
                                    class:checked={userData.preference.includes(option)}
                                    on:click={() => checkOption(option)}
                                >
                                    {option}
                                </span>
                            </label>
                        {/each}
                    </div>
                </fieldset>
            </div>
            <div>
                <button class="cancel" type="reset">Cancel</button>
                <button class="confirm" on:click={setUser} >Confirm</button>
            </div>
        </form>
    </div>
</main>

<style>
    .setCenter {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .profileImage{
        border-radius: 50%;
        height: 100px;
        width: 100px;
        margin-bottom: 10px;
        cursor: pointer;
    }

    .form-box {
        position: absolute;
        top: 10%;
        width: 75%;
        max-width: 600px;
        background: #f1f7fe;
        overflow: hidden;
        border-radius: 16px;
        color: #333;
    }

    .form {
        position: relative;
        display: flex;
        flex-direction: column;
        padding: 32px 24px 24px;
        gap: 16px;
        text-align: center;
        align-items: center;
    }

    /*Form text*/
    .title {
        font-weight: bold;
        font-size: 1.6rem;
        display: flex;
        text-align: center;
        margin-bottom: 10px;
    }

    /*Inputs box*/
    .form-container {
        overflow: hidden;
        border-radius: 8px;
        background-color: #fff;
        margin: 1rem 0 0.5rem;
        font-size: 0.9rem;
        width: 90%;
    }

    .input {
        font-size: 18px;
        background: none;
        border: 0;
        outline: 0;
        height: 40px;
        width: 100%;
        border-bottom: 1px solid #eee;
        padding: 8px 15px;
        margin-bottom: 2px;
    }

    .nonclick{
        pointer-events: none;
        cursor: not-allowed;
    }

    .name{
        font-size: 1.4rem;
        font-weight: 500;
        display: flex;
        text-align: center;
        margin-bottom: 0px;
    }

    .last{
        margin-bottom: 0px;
    }

    /* fieldset */
    fieldset {
        border: none;
        padding: 0;
        margin: 1rem 0;
    }

    fieldset legend {
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 20px;
    }

    .preferences {
        display: flex;
        justify-content: center;
        flex-wrap: wrap;
        gap: 12px;
    }

    .preferences span {
        padding: 5px 10px;
        background-color: #f0f0f0;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    .preferences span:hover {
        background-color: #0055d4;
        color: #f0f0f0;
    }

    .preferences span.checked {
        background-color: #0066ff;
        color: #f0f0f0;
    }

    /*Button*/
    .form button {
        color: #fff;
        border: 0;
        border-radius: 15px;
        padding: 10px 16px;
        font-size: 1rem;
        font-weight: 600;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .confirm {
        background-color: #0066ff;
    }

    .form .confirm:hover {
        background-color: #0055d4;
    }

    .cancel {
        background-color: #c9c9c9;
    }

    .form .cancel:hover {
        background-color: #a0a0a0;
    }
</style>
