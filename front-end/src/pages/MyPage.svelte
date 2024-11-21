<main>
    <div class="input-group">
        <input
            required=""
            type="text"
            name="text"
            autocomplete="off"
            class="input"
        />
        <label class="user-label">First Name</label>
    </div>
    <div class="input-group">
        <input
            required=""
            type="text"
            name="text"
            autocomplete="off"
            class="input"
        />
        <label class="user-label">First Name</label>
    </div>
    <div class="input-group">
        <input
            required=""
            type="text"
            name="text"
            autocomplete="off"
            class="input"
        />
        <label class="user-label">First Name</label>
    </div>
</main>

<style>
    .input-group {
        position: relative;
        margin: 40px;
    }

    .input {
        border: solid 1.5px #9e9e9e;
        border-radius: 1rem;
        background: none;
        padding: 1rem;
        font-size: 1rem;
        color: #f5f5f5;
        transition: border 150ms cubic-bezier(0.4, 0, 0.2, 1);
    }

    .user-label {
        position: absolute;
        left: 15px;
        bottom: 40px;
        color: #e8e8e8;
        pointer-events: none;
        transform: translateY(1rem);
        transition: 150ms cubic-bezier(0.4, 0, 0.2, 1);
    }
    input:valid {
        outline: none;
        border: 1.5px solid #1a73e8;
    }

    .input:focus ~ label,
    input:valid ~ label {
        transform: translateY(-50%) scale(0.8);
        background-color: #ffffff;
        padding: 0 0.2em;
        color: #2196f3;
    }
</style>
