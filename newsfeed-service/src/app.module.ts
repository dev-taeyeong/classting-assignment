import { Module } from '@nestjs/common';
import { NewsfeedsModule } from './newsfeeds/newsfeeds.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Newsfeed } from './newsfeeds/entities/newsfeed.entity';
import { ClientsModule, Transport } from '@nestjs/microservices';
import { ConfigModule } from '@nestjs/config';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: 'mysql',
      port: 3306,
      username: 'root',
      password: 'admin1234',
      database: 'news_feed',
      entities: [Newsfeed],
      synchronize: true,
    }),
    ClientsModule.register([
      {
        name: 'KAFKA_SERVICE',
        transport: Transport.KAFKA,
        options: {
          client: {
            brokers: ['kafka:9092'],
          },
          consumer: {
            groupId: 'my_consumer'
          }
        },
      },
    ]),
    ConfigModule.forRoot({
      isGlobal: true,
    }),
    NewsfeedsModule
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
