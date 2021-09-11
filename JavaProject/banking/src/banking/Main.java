package banking;

import java.util.Scanner;

public class Main {
	private static Account[] accountArray = new Account[100];
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		boolean run = true;
		
		try {
			while(run) {
				System.out.println("=============================================");
				System.out.println("1.계좌 생성 | 2.계좌 목록 | 3.예금 | 4.출금 | 5.종료");	
				System.out.println("=============================================");
				System.out.print("선택>");
				
				int selNum = scan.nextInt();
				if(selNum == 1) {
					createAccount();
				}
				else if(selNum == 2) {
					accountList();
				}
				else if(selNum == 3) {
					deposit();
				}
				else if(selNum == 4) {
					withdraw();
				}
				else if(selNum == 5) {
					run = false;
				}
				else {
					System.out.println("지원하지 않는 기능입니다.");
				}
				
			}
		}catch(Exception e) {
			System.out.println("잘못된 입력입니다.");
		}finally {
			scan.close();
		}
		System.out.println("프로그램 종료");
		scan.close();
		
	}
	
	// 계좌 생성
	private static void createAccount() {
		System.out.println("=========");
		System.out.println("1.계좌 생성 ");	
		System.out.println("=========");
		
		System.out.println("계좌 번호 : ");	
		String ano = scan.next();
		
		System.out.println("계좌주 : ");	
		String owner = scan.next();
		
		System.out.println("초기 입금액 : ");	
		int balance = scan.nextInt();
		
		Account newAccount = new Account(ano, owner, balance);
		for(int i = 0; i < accountArray.length; i++) {
			if(accountArray[i] == null) {		// 주의!!(null 처리) : 비어있는 인덱스에 새 계좌가 저장됨
				accountArray[i] = newAccount;
				System.out.println("결과 : 계좌가 생성되었습니다.");
				break;
			}
		}
	}

	// 계좌 목록
	private static void accountList() {
		System.out.println("=========");
		System.out.println("2.계좌 목록 ");	
		System.out.println("=========");
		
		// 저장된 모든 계좌 출력(null 이 아닌 계좌)
		for(int i = 0; i < accountArray.length; i++) {
			Account account = accountArray[i];
			if(account != null) {		// 주의!! null이 아닌경우 출력
				System.out.print("계좌번호 : " + account.getAno() + "   ");
				System.out.print("계좌주 : " + account.getOwner() + "   ");
				System.out.println("잔고 : " + account.getBalance() + "   ");
			}
		}
	}

	// 예금
	private static void deposit() {
		System.out.println("=========");
		System.out.println("3.예금 ");	
		System.out.println("=========");
		
		System.out.print("계좌 번호 : ");	
		String ano = scan.next();
		
		Account account = findAccount(ano);
		
		if(account == null) {
			System.out.println("없는 계좌 입니다.");
		}
		else {
			while(true) {
				System.out.print("입금액 : ");	
				int money = scan.nextInt();
				if(money <= 0) {
					System.out.println("0보다 큰 금액을 입력하십시오.");
				}
				else {
					account.setBalance(account.getBalance() + money);
					System.out.println("결과 : 입금을 성공했습니다.");
					break;
				}
				
			}
			
		}
		
	}
	
	// 계좌 검색
	private static Account findAccount(String ano) {
		Account account = null;
		for(int i = 0; i < accountArray.length; i++) {
			if(accountArray[i] != null) {
				String dbAno = accountArray[i].getAno();
				if(dbAno.equals(ano)) {		// 이미 저장된 계좌번호와 외부에서 입력받은 계좌번호가 일치하면
					account = accountArray[i];
					break;
				}
			}
		}
		return account;
	}

	// 출금
	private static void withdraw() {
		System.out.println("=========");
		System.out.println("4.출금 ");	
		System.out.println("=========");
		
		System.out.print("계좌 번호 : ");	
		String ano = scan.next();
		
		Account account = findAccount(ano);
		
		if(account == null) {
			System.out.println("없는 계좌 입니다.");
		}
		else {
			while(true) {
				System.out.print("출금액 : ");	
				int money = scan.nextInt();
			
				if(money > account.getBalance() || money < 0) {
					System.out.println("잔액이 부족합니다.");
					System.out.println("다시 입력해주세요.");
				}
				else {
					account.setBalance(account.getBalance() - money);
					System.out.println("결과 : 출금을 성공했습니다.");
					break;
				}
				
			}
		
		}
		
	}

}
