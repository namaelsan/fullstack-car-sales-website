import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { HTTP_INTERCEPTORS, provideHttpClient } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { MatNativeDateModule } from '@angular/material/core';
import { AuthService } from './auth.service';
import { TokenInterceptor } from './token.interceptor';


export const appConfig: ApplicationConfig = {
  providers: [provideHttpClient(), 
    provideRouter(routes), 
    provideAnimations(), 
    importProvidersFrom(MatNativeDateModule),
    AuthService,
    {provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ]
};
