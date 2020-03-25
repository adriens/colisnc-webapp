import { FromNowPipe } from './from-now.pipe';

describe('FromNowPipe', () => {

  it('should transform the input', (done) => {
    // given a pipe
    const pipe = new FromNowPipe();

    // when transforming the date
    const date = '2016-02-18T08:02:00Z';
    const transformed = pipe.transform(date);

    transformed.subscribe(data => {
      // then we should have a formatted string
      expect(data).toContain('ago',
        'The pipe should transform the date into a human string, using the `fromNow` method of Moment.js');
      done();
    });
  });
});
