package br.com.geradorOkaeri.model;

import java.util.Arrays;

import br.com.geradorOkaeri.Util.Mensagem;
import br.com.geradorOkaeri.Util.Mensagem.MensagemEnum;

public enum Qualidade {
	TRES_D(1, "3D"), 
	BD_25(2, "BD-25"), 
	BD_RIP(3, "BDRip"), 
	BLU_RAY(4, "Blu Ray"), 
	BR_RIP(5, "BRRip"), 
	CAM(6, "CAM"), 
	DVD_5(7, "DVD-5"), 
	DVD_9(8, "DVD-9"), 
	DVD_RIP(9, "DVDrip"), 
	DVD_R(10, "DVD-R"), 
	DVD_SCREEN(11, "DVDScreen"), 
	HDDVD(12, "HDDVD"), 
	HD_720P(13, "HD 720p"), 
	HD_1080P(14, "HD 1080p"), 
	HDTV(15, "HDTV"), 
	PDTV(16, "PDTV"), 
	R5(17, "R5"), 
	RAT_DVD(18, "RatDVD"), 
	REMUX(19, "Remux"), 
	SCREENER(20, "Screener"), 
	TELE_CINE(21, "TeleCine"), 
	TELE_SYNC(22, "TeleSync"), 
	TV_RIP(23, "TVRip"), 
	VHS_RIP(24, "VHSRip"), 
	WEB_RIP(25, "WEBRip"), 
	WEB_DL(26, "WEB-DL"), 
	WORKPRINT(28, "Workprint"), 
	OUTROS(29, "Outros");
	
	private String name;
	private int id;

	private Qualidade(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public static Qualidade valueOf(int id) {
		return Arrays.asList(Qualidade.values()).stream().filter(i -> i.getId() == id).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						Mensagem.get(MensagemEnum.Mensagem_Error_Enum_Param, id, Qualidade.class.getName())));
	}

}
