import { Controller, Get, Param, Delete, Query } from '@nestjs/common';
import { NewsfeedsService } from './newsfeeds.service';
import { CreateNewsfeedDto } from './dto/create-newsfeed.dto';
import { Ctx, KafkaContext, MessagePattern, Payload } from '@nestjs/microservices';

@Controller('api/v1/newsfeeds')
export class NewsfeedsController {
  constructor(private readonly newsfeedsService: NewsfeedsService) {}

  @MessagePattern('SchoolNewsPublished-topic')
  async handleNewsfeedCreated(@Payload() message: CreateNewsfeedDto, @Ctx() context: KafkaContext) {
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
