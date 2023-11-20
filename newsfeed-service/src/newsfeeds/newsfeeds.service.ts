import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Newsfeed } from './entities/newsfeed.entity';
import { Repository } from 'typeorm';
import { ConfigService } from '@nestjs/config';
import axios from 'axios';

@Injectable()
export class NewsfeedsService {

  constructor(
    @InjectRepository(Newsfeed)
    private readonly newsfeedRepository: Repository<Newsfeed>,
    private readonly configService: ConfigService,
  ) { }

  async handleNewsfeedCreated(message: any) {
    const baseUrl = this.configService.get<string>('STUDENT_SUBSCRIPTION_SERVICE_HOST');
    const response = await axios.get(`${baseUrl}/api/v1/school-pages/${message.schoolPageId}/subscribers`);

    const newsFeeds = response.data.map(studentId => this.newsfeedRepository.create({ studentId, newsPostId: message.newsPostId }));

    await this.newsfeedRepository.save(newsFeeds);
  }

  async findAll() {
    return await this.newsfeedRepository.find();
  }

  async findByStudentId(studentId: number, page: number, size: number) {
    const newsfeeds = await this.newsfeedRepository.find({
      where: {
        studentId
      },
      order: {
        id: 'DESC'
      },
      skip: page * size,
      take: size
    });

    const newsPostIds = newsfeeds.map(newsfeed => newsfeed.newsPostId);

    const params = {
      newsPostIds: newsPostIds.join(',')
    };

    const baseUrl = this.configService.get<string>('SCHOOL_NEWS_PUBLISHING_SERVICE_HOST');
    console.log(baseUrl);

    const response = await axios.get(`${baseUrl}/api/v1/news-posts/by-ids`, { params });

    const newsPosts = response.data;
    const orderedNewsPosts = newsPostIds
      .filter(id => newsPosts.some(post => post.id === id))
      .map(id => newsPosts.find(post => post.id === id));

    return orderedNewsPosts;
  }

  remove(id: number) {
    return `This action removes a #${id} newsfeed`;
  }

  async onModuleInit() {
    this.newsfeedRepository.save(
      [
        this.newsfeedRepository.create(
          {
            id: 1,
            studentId: 1,
            newsPostId: "테스트 ID 1"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 2,
            studentId: 4,
            newsPostId: "테스트 ID 1"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 3,
            studentId: 5,
            newsPostId: "테스트 ID 1"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 4,
            studentId: 7,
            newsPostId: "테스트 ID 1"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 5,
            studentId: 1,
            newsPostId: "테스트 ID 2"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 6,
            studentId: 2,
            newsPostId: "테스트 ID 2"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 7,
            studentId: 8,
            newsPostId: "테스트 ID 2"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 8,
            studentId: 10,
            newsPostId: "테스트 ID 2"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 9,
            studentId: 1,
            newsPostId: "테스트 ID 3"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 10,
            studentId: 3,
            newsPostId: "테스트 ID 3"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 11,
            studentId: 7,
            newsPostId: "테스트 ID 3"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 12,
            studentId: 10,
            newsPostId: "테스트 ID 3"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 13,
            studentId: 3,
            newsPostId: "테스트 ID 4"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 14,
            studentId: 5,
            newsPostId: "테스트 ID 4"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 15,
            studentId: 8,
            newsPostId: "테스트 ID 4"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 16,
            studentId: 10,
            newsPostId: "테스트 ID 4"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 17,
            studentId: 1,
            newsPostId: "테스트 ID 5"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 18,
            studentId: 6,
            newsPostId: "테스트 ID 5"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 19,
            studentId: 7,
            newsPostId: "테스트 ID 5"
          }
        ),
        this.newsfeedRepository.create(
          {
            id: 20,
            studentId: 9,
            newsPostId: "테스트 ID 5"
          }
        ),
      ]
    );
  }
}
