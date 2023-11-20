import { Controller, Get, Post, Body, Patch, Param, Delete, Query } from '@nestjs/common';
import { NewsfeedsService } from './newsfeeds.service';
import { CreateNewsfeedDto } from './dto/create-newsfeed.dto';
import { Ctx, KafkaContext, MessagePattern, Payload } from '@nestjs/microservices';


@Controller('newsfeeds')
export class NewsfeedsController {
  constructor(private readonly newsfeedsService: NewsfeedsService) {}

  @Post()
  create(@Body() createNewsfeedDto: CreateNewsfeedDto) {
    console.log(createNewsfeedDto.studentId);
    return this.newsfeedsService.create(createNewsfeedDto);
  }

  @MessagePattern('SchoolNewsPublished-topic')
  async handleNewsfeedCreated(@Payload() message: any, @Ctx() context: KafkaContext) {
    this.newsfeedsService.handleNewsfeedCreated(message);
  }

  @Get()
  findAll() {
    return this.newsfeedsService.findAll();
  }

  @Get('by-student-id/:studentId')
  findByStudentId(
    @Param('studentId') studentId: string,
    @Query('page') page: number = 0,
    @Query('size') size: number = 20,
  ) {
    return this.newsfeedsService.findByStudentId(+studentId, page, size);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.newsfeedsService.remove(+id);
  }
}
