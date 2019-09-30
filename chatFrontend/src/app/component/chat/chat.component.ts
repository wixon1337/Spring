import { Component, OnInit } from '@angular/core';
import { SocketService } from 'src/app/service/socket.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import * as SockJs from 'sockjs-client';
import * as Stomp from 'stompjs';
import { environment } from '../../../environments/environment';
import { Message } from 'src/app/model/Message';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  private form: FormGroup;
  private userForm: FormGroup;
  private serverUrl = environment.url + 'socket';
  private stompClient;
  private isLoaded: boolean = false;
  private isCustomSocketOpened: boolean = false;
  messages: Message[] = [];

  constructor(private socketService: SocketService) { }

  ngOnInit() {
    this.form = new FormGroup({
      message: new FormControl(null, [Validators.required])
    })

    this.userForm = new FormGroup({
      fromId: new FormControl(null, [Validators.required]),
      toId: new FormControl(null)
    })

    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection() {
    let ws = new SockJs(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function () {
      that.isLoaded = true;
    })
  }

  openSocket() {
    if (this.isLoaded) {
      this.isCustomSocketOpened = true;
      this.stompClient.subscribe("/socket-publisher/" + this.userForm.value.fromId, (message) => {
        this.handleResult(message);
      });
    }
  }

  handleResult(message) {
    if (message.body) {
      let messageResult: Message = JSON.parse(message.body);
      console.log(messageResult);
      this.messages.push(messageResult);
    }
  }

  sendMessageUsingSocket() {
    if (this.form.valid) {
      let message: Message = { message: this.form.value.message, fromId: this.userForm.value.fromId, toId: this.userForm.value.toId };
      this.stompClient.send("/socket-subscriber/send/message", {}, JSON.stringify(message));
    }
  }

}
