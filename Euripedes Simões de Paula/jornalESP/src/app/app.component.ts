import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { MenuComponent } from "./menu/menu.component";
import { RodapeComponent } from './rodape/rodape.component';
import { NewsComponent } from "./news/news.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, MenuComponent, RodapeComponent, InicioComponent, NewsComponent]
})
export class AppComponent {
  title = 'jornalESP';
}
