<script>
    let userData = {        // 유저 프로필 (임시)
        image: 'https://via.placeholder.com/100',  // 프로필 이미지
        uid: 'rang',        // 아이디 
        password: '123',    // 비밀번호
        email: 'rang@gmail.com',      // 이메일
        uname: '고해랑',       // 닉네임
        favorite1: '',      // 선호음식1
        favorite2: '',      // 선호음식2
    };
</script>

<main class="setCenter">
    <div class="form-box">
        <form class="form" on:submit|preventDefault={() => console.log(userData)}>
            <span class="title">프로필</span>
            <div>
                {#if userData.image}
                    <img src={userData.image} alt="Image" class="profileImage"/>
                {:else}
                    <span>이미지 첨부</span>
                {/if}
            </div>
            
            <input type="text" class="input name" bind:value={userData.uname} placeholder="Name"/>
            <div class="form-container">
                <input type="ID" class="input" bind:value={userData.uid} placeholder="ID" />
                <input type="password" class="input" bind:value={userData.password} placeholder="Password" />
                <input type="password" class="input" placeholder="Password Confirm"/>
                <input type="email" class="input" bind:value={userData.email} placeholder="Email"/>
                <input type="text" class="input last" bind:value={userData.favorite1} placeholder="Favorite1"/>
                <input type="text" class="input last" bind:value={userData.favorite2} placeholder="Favorite2"/>
            </div>
            <div>
                <button type="reset">Cancel</button>
                <button type="submit">Confirm</button>
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
        top: 15%;
        max-width: 350px;
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

    .subtitle {
        font-size: 1rem;
        color: #666;
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
        background: none;
        border: 0;
        outline: 0;
        height: 40px;
        width: 100%;
        border-bottom: 1px solid #eee;
        padding: 8px 15px;
    }

    .name{
        font-size: 1.4rem;
        display: flex;
        text-align: center;
        margin-bottom: 0px;
    }

    .last{
        margin-bottom: 0px;
    }
    .form-section {
        padding: 16px;
        font-size: 0.85rem;
        background-color: #e0ecfb;
        box-shadow: rgb(0 0 0 / 8%) 0 -1px;
    }

    .form-section a {
        font-weight: bold;
        color: #0066ff;
        transition: color 0.3s ease;
    }

    .form-section a:hover {
        color: #005ce6;
        text-decoration: underline;
    }

    /*Button*/
    .form button {
        background-color: #0066ff;
        color: #fff;
        border: 0;
        border-radius: 15px;
        padding: 10px 16px;
        font-size: 1rem;
        font-weight: 600;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .form button:hover {
        background-color: #005ce6;
    }
</style>
