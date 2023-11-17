import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity()
export class Newsfeed {

  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  studentId: number;

  @Column()
  newsPostId: string;
}
