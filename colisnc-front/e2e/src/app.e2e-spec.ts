import { AppPage } from './app.po';
import { browser, logging } from 'protractor';
import { AuthenticatePage } from './authenticate.po';

describe('basic use case : user get connected and update cras', () => {
  let authenticatePage: AuthenticatePage;
  let page: AppPage;

  beforeEach(() => {
    authenticatePage = new AuthenticatePage();
    page = new AppPage();
  });

  it('should authenticate and go to main page', () => {
    page.navigateTo('2017-07');

    // but first, login
    authenticatePage.login.sendKeys('j.valjean');
    authenticatePage.password.sendKeys('mot-de-passe-ok');
    authenticatePage.button.click();

    // now, we should be in home page
    expect(page.getCurrentMonthText()).toEqual('juillet 2017');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
