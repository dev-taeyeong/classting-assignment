import { Module } from '@nestjs/common';
import { NewsfeedsModule } from './newsfeeds/newsfeeds.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Newsfeed } from './newsfeeds/entities/newsfeed.entity';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: '127.0.0.1',
      port: 3306,
      username: 'root',
      password: 'admin1234',
      database: 'news_feed',
      entities: [Newsfeed],
      synchronize: true,
    }),
    NewsfeedsModule
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
