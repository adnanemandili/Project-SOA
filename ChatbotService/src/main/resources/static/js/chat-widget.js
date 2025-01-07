class ChatWidget {
    constructor() {
        this.createWidget();
        this.initializeEvents();
    }

    createWidget() {
        const widget = document.createElement('div');
        widget.className = 'chat-widget';
        widget.innerHTML = `
            <button class="chat-toggle">Chat</button>
            <div class="chat-popup">
                <div class="chat-messages"></div>
                <input type="text" class="chat-input" placeholder="Posez votre question...">
            </div>
        `;
        document.body.appendChild(widget);
    }

    initializeEvents() {
        const toggle = document.querySelector('.chat-toggle');
        const popup = document.querySelector('.chat-popup');
        const input = document.querySelector('.chat-input');

        toggle.addEventListener('click', () => {
            popup.style.display = popup.style.display === 'none' ? 'block' : 'none';
        });

        input.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                this.sendMessage(input.value);
                input.value = '';
            }
        });
    }

    async sendMessage(message) {
        try {
            const response = await fetch('/api/chatbot/ask', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ question: message })
            });
            const data = await response.json();
            this.displayMessage(message, data.answer);
        } catch (error) {
            console.error('Error:', error);
        }
    }

    displayMessage(question, answer) {
        const messages = document.querySelector('.chat-messages');
        messages.innerHTML += `
            <div class="message">
                <div class="question">${question}</div>
                <div class="answer">${answer}</div>
            </div>
        `;
        messages.scrollTop = messages.scrollHeight;
    }
}