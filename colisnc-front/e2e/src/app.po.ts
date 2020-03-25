import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo(month?: string) {
    return browser.get(`${browser.baseUrl}${month}`) as Promise<any>;
  }

  getCurrentMonthText() {
    return element(by.css('.app-period')).getText() as Promise<string>;
  }
}
