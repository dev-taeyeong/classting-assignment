import { Test, TestingModule } from '@nestjs/testing';
import { NewsfeedsService } from './newsfeeds.service';
import { Repository } from 'typeorm';
import { Newsfeed } from './entities/newsfeed.entity';
import { getRepositoryToken } from '@nestjs/typeorm';

describe('NewsfeedsService', () => {
  let service: NewsfeedsService;
  let repository: Repository<Newsfeed>;

  const mockNewsfeedRepository = {
    create: jest.fn(),
    save: jest.fn(),
    find: jest.fn(),
  };

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        NewsfeedsService,
        {
          provide: getRepositoryToken(Newsfeed),
          useValue: mockNewsfeedRepository,
        }
      ],
    }).compile();

    service = module.get<NewsfeedsService>(NewsfeedsService);
    repository = module.get<Repository<Newsfeed>>(getRepositoryToken(Newsfeed));
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  describe('create', () => {
    it('should create and return a newsfeed', async () => {
      const createNewsfeedDto = { studentId: 1, newsPostId: '123' };
      const expectedNewsfeed = { id: 1, ...createNewsfeedDto };

      jest.spyOn(repository, 'create').mockReturnValue(expectedNewsfeed);
      jest.spyOn(repository, 'save').mockResolvedValue(expectedNewsfeed);

      const result = await service.create(createNewsfeedDto);
      expect(result).toEqual(expectedNewsfeed);
    });
  });
});
