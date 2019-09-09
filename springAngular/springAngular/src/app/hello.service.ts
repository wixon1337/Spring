import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HelloService {

  constructor(private httpClient: HttpClient) { }

  public getData() {
    return this.httpClient.get("http://localhost:8080/api/hello");
  }
}
