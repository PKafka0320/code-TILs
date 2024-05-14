import java.io.*;
import java.util.*;

import javax.security.auth.Subject;

class Prerequisite implements Comparable<Prerequisite> {
    int preSubject, subject;
    
    public Prerequisite(int preSubject, int subject) {
        this.preSubject = preSubject;
        this.subject = subject;
    }
    
    @Override
    public int compareTo(Prerequisite p) {
        if (this.preSubject == p.preSubject) return this.subject - p.subject;
        return this.preSubject - p.preSubject;
    }
    
    @Override
    public String toString() {
        return "[" + this.preSubject + "," + this.subject + "]";
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 과목의 수
        int M = Integer.parseInt(tokenizer.nextToken()); // 선수 조건의 수
        
        int[] semester = new int[N + 1]; // 과목별 최초 이수 학기
        Prerequisite[] prerequisites = new Prerequisite[M]; // 선수 과목
        
        // 최초 이수 학기 초기화
        for (int subject = 1; subject <= N; subject++) {
            semester[subject] = 1;
        }
        
        // 선수과목 배열 생성
        for (int idx = 0; idx < M; idx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int preSubject = Integer.parseInt(tokenizer.nextToken());
            int subject = Integer.parseInt(tokenizer.nextToken());
            
            prerequisites[idx] = new Prerequisite(preSubject, subject);
        }
        
        // 선수과목 정렬
        Arrays.sort(prerequisites);
        
        // 이수 학기 계산
        for (Prerequisite prerequisite : prerequisites) {
            int preSubject = prerequisite.preSubject;
            int subject = prerequisite.subject;
            
            semester[subject] = Math.max(semester[subject], semester[preSubject] + 1);
        }
        
        StringBuilder answer = new StringBuilder();
        for (int subject = 1; subject <= N; subject++) {
            answer.append(semester[subject]).append(" ");
        }
        System.out.println(answer);
    }
}
