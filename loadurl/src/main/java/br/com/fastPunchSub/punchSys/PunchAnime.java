package br.com.fastPunchSub.punchSys;

import java.io.Serializable;

import br.com.fastPunchSub.punchSys.util.AnimeType;
import br.com.fastPunchSub.punchSys.util.QualityPunch;
import br.com.fastPunchSub.punchSys.util.StatusAnimes;

public class PunchAnime implements Serializable{
	private static final long serialVersionUID = -1831041820315709306L;
	
	// idx 0
	private String id;
	// idx 1
	private String name;
	// idx 2
	private Integer totalEpisodes;
	// idx 2
	private Integer EpisodesSubtitles;
	// idx 3
	private String[] genere;
	// idx 4
	private StatusAnimes status;
	// idx 5
	private String youtube;
	// idx 6
	private String sinopse;
	// idx 7
	private QualityPunch[] quality;
	// idx 8
	private AnimeType type;
	// idx 9
	private String animePath;
	// idx 10
	private boolean flagNaoSeiOQue;
	

}
