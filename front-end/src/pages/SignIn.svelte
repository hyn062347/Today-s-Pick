<script>
    import { user } from '../store';
    let formData = {
        uid:'',
        password:'',
    }


    async function sendData(){
        try{
            const response = await fetch('http://localhost:8080/api/account/signin',{
                method: 'POST',
                headers:{
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData),
                credentials:'include',
            });

            if(!response.ok){
                throw new Error(`서버오류: ${response.status}`);
            }

            const result = await response.json();
            user.set(result);
            sessionStorage.setItem("idkey",result.uid);
            console.log('서버 응답', user);
            window.location.href='/';
        }catch (error){
            console.error(`데이터 전송 오류`, error);
            user.set(null);
        }
        
    }
</script>

<main class="setCenter">
    <div class="form-box">
        <form class="form" on:submit|preventDefault={sendData}>
            <span class="title">Sign In</span>
            <div class="form-container">
                <input type="ID" class="input" placeholder="ID" bind:value={formData.uid}/>
                <input type="password" class="input" placeholder="Password" bind:value={formData.password}/>
            </div>
            <button type="submit">Sign In</button>
        </form>
        <div class="form-section">
            <p>Don't an account? <a href="/signup">Sign Up</a></p>
        </div>
    </div>
</main>

<style>
    .setCenter {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .form-box {
        position: absolute;
        top: 30%;
        max-width: 300px;
        background: #f1f7fe;
        overflow: hidden;
        border-radius: 16px;
        color: #010101;
    }

    .form {
        position: relative;
        display: flex;
        flex-direction: column;
        padding: 32px 24px 24px;
        gap: 16px;
        text-align: center;
    }

    /*Form text*/
    .title {
        font-weight: bold;
        font-size: 1.6rem;
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
        width: 100%;
    }

    .input {
        background: none;
        border: 0;
        outline: 0;
        height: 40px;
        width: 100%;
        border-bottom: 1px solid #eee;
        font-size: 0.9rem;
        padding: 8px 15px;
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
