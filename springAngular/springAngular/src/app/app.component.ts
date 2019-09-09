import { Component } from '@angular/core';
import { HelloService } from './hello.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'springAngular';

  constructor(private helloService: HelloService) {
    this.helloService.getData().subscribe(param => console.log(param));
  }
}
