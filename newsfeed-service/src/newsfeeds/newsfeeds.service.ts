import { Injectable } from '@nestjs/common';
import { CreateNewsfeedDto } from './dto/create-newsfeed.dto';
import { UpdateNewsfeedDto } from './dto/update-newsfeed.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Newsfeed } from './entities/newsfeed.entity';
import { Repository } from 'typeorm';
import axios from 'axios';

@Injectable()
export class NewsfeedsService {

  constructor(
    @InjectRepository(Newsfeed)
    private readonly newsfeedRepository: Repository<Newsfeed>,
  ) {}

  async handleNewsfeedCreated(message: any) {
    console.log(message);

    const response = await axios.get(`http://student-subscription-service:8082/api/v1/school-pages/${message.schoolPageId}/subscribers`);

    const data: number[] = response.data;

    const newsFeeds = data.map(studentId => this.newsfeedRepository.create({ studentId, newsPostId: message.newsPostId }));
    console.log(newsFeeds);

    await this.newsfeedRepository.save(newsFeeds);
  }

  async create(createNewsfeedDto: CreateNewsfeedDto) {
    const newsFeedToSave = this.newsfeedRepository.create({
      studentId: +createNewsfeedDto.studentId,
      newsPostId: createNewsfeedDto.newsPostId
    });
    const newsFeed = await this.newsfeedRepository.save(newsFeedToSave);
    return newsFeed;
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

    const response = await axios.get("http://school-news-publishing-service:8081/api/v1/news-posts/by-ids", {params});

    const newsPosts = response.data;
    const orderedNewsPosts = newsPostIds
      .filter(id => newsPosts.some(post => post.id === id))
      .map(id => newsPosts.find(post => post.id === id));

    return orderedNewsPosts;
  }

  remove(id: number) {
    return `This action removes a #${id} newsfeed`;
  }
}
