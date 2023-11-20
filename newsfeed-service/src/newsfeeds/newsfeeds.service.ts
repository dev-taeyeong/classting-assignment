import { Injectable } from '@nestjs/common';
import { CreateNewsfeedDto } from './dto/create-newsfeed.dto';
import { UpdateNewsfeedDto } from './dto/update-newsfeed.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Newsfeed } from './entities/newsfeed.entity';
import { Repository } from 'typeorm';
import axios from 'axios';
import { ClientKafka, MessagePattern, Payload } from '@nestjs/microservices';

@Injectable()
export class NewsfeedsService {

  constructor(
    @InjectRepository(Newsfeed)
    private readonly newsfeedRepository: Repository<Newsfeed>,
  ) {}

  async handleNewsfeedCreated(message: any) {
    console.log(message);

    const params = {
      newsPostIds: message.schoolPageId
    };

    const response = await axios.get("http://school-news-publishing-service:8081/api/v1/news-posts/by-ids", {params});
    console.log(response.data);
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

    const params = {
      newsPostIds: newsfeeds.map(newsfeed => newsfeed.newsPostId).join(',')
    };

    const response = await axios.get("http://localhost:8081/api/v1/news-posts/by-ids", {params});
    return response.data;
  }

  remove(id: number) {
    return `This action removes a #${id} newsfeed`;
  }
}
