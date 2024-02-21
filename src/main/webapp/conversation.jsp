<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html>
<html lang="en">
<head>
  Include jQuery
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f2f2f2;
    }

    #chat-container {
      width: 400px;
      margin: 50px auto;
      border: 1px solid #ccc;
      border-radius: 10px;
      overflow: hidden;
    }

    #chat-messages {
      height: 300px;
      overflow-y: scroll;
      padding: 10px;
    }

    #user-input {
      width: 100%;
      padding: 10px;
      box-sizing: border-box;
      border: none;
      border-top: 1px solid #ccc;
    }
  </style>
  <title>Simple Chat Interface</title>
</head>
<body>
  <div id="chat-container">
    <div id="chat-messages"> kjhjh </div>
    <input type="text" id="user-input" placeholder="Type your message..." onkeydown="sendMessage(event)">
  </div>

  <script>
    function sendMessage(event) {
      if (event.key === 'Enter') {
        const userInput = document.getElementById('user-input');
        const usmessage = document.querySelector("#chat-messages")
        
        const message = userInput.value;
        

        if (message.trim() !== '') {
          appendMessage('You', message);
          userInput.value = ''; 


          // Send the message to the Spring Controller
          sendToSpringController(message);
        }
      }
    }

    function sendToSpringController(message) {
      // Use jQuery to make an AJAX request
      $.ajax({
        type: 'POST',
        url: '/processMessage', // The endpoint in your Spring Controller
        contentType: 'application/json',
        data: JSON.stringify(message),
        success: function (response) {
          console.log(response);
        },
        error: function (error) {
          console.error('Error sending message to server:', error);
        }
      });
    }

    function appendMessage(sender, text) {
      const chatMessages = document.getElementById('chat-messages');
      const messageDiv = document.createElement('div');
      messageDiv.innerHTML = `<strong>${sender}:</strong> ${text}`;
      chatMessages.appendChild(messageDiv);
      chatMessages.scrollTop = chatMessages.scrollHeight;
    }
  </script>
</body>
</html> -->



<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Include jQuery -->
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f2f2f2;
    }

    #chat-container {
      width: 400px;
      margin: 50px auto;
      border: 1px solid #ccc;
      border-radius: 10px;
      overflow: hidden;
    }

    #chat-messages {
      height: 300px;
      overflow-y: scroll;
      padding: 10px;
    }

    #user-input {
      width: 100%;
      padding: 10px;
      box-sizing: border-box;
      border: none;
      border-top: 1px solid #ccc;
    }
  </style>
  <title>Simple Chat Interface</title>
</head>
<body>
  <div id="chat-container">
    <div id="chat-messages">  </div>
    <input type="text" id="user-input" placeholder="Type your message..." onkeydown="sendMessage(event)">
  </div>

  <script>
    function sendMessage(event) {
      if (event.key === 'Enter') {
        const userInput = document.getElementById('user-input');
        const usmessage = document.querySelector("#chat-messages")
        
        const message = userInput.value;
        

        if (message.trim() !== '') {
          appendMessage('You', message);
          userInput.value = '';
          


          // Send the message to the Spring Controller
          sendToSpringController(message);
        }

        
      }
    }

    function sendToSpringController(message) {
      // Use jQuery to make an AJAX request
      $.ajax({
        type: 'POST',
        url: '/processMessage', // The endpoint in your Spring Controller
        contentType: 'application/json',
        data: JSON.stringify(message),
        success: function (response) {
          console.log(response);
        },
        error: function (error) {
          console.error('Error sending message to server:', error);
        }
      });
    }

    function appendMessage(sender, text) {
      const chatMessages = document.getElementById('chat-messages');
      const messageDiv = document.createElement('div');
      messageDiv.innerHTML = `<strong>${sender}:</strong> ${text}`;
      chatMessages.appendChild(messageDiv);
      chatMessages.scrollTop = chatMessages.scrollHeight;
    }
  </script>
</body>
</html>