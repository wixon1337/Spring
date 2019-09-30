import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment'
import { Message } from '../model/Message';
import { HttpClient } from '@angular/common/http';
/* import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch'; */

@Injectable({
  providedIn: 'root'
})
export class SocketService {

  url: string = environment.url + "api/socket";

  constructor(private httpClient: HttpClient) { }

  post(data: Message) {
    return this.httpClient.post(this.url, data);
  }
}
