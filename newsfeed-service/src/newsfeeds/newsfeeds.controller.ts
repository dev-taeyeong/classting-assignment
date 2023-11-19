import { Controller, Get, Post, Body, Patch, Param, Delete, Query } from '@nestjs/common';
import { NewsfeedsService } from './newsfeeds.service';
import { CreateNewsfeedDto } from './dto/create-newsfeed.dto';

@Controller('newsfeeds')
export class NewsfeedsController {
  constructor(private readonly newsfeedsService: NewsfeedsService) {}

  @Post()
  create(@Body() createNewsfeedDto: CreateNewsfeedDto) {
    console.log(createNewsfeedDto.studentId);
    return this.newsfeedsService.create(createNewsfeedDto);
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
