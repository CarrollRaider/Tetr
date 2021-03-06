package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import config.GameConfig;
import dto.Player;

public abstract class LayerData extends Layer {

	/*
	 * 最大数据行
	 */
	private static final int MAX_ROM = GameConfig.getDataConfig().getMaxRow();
	/*
	 *起始Y坐标 
	 */
	private static int START_Y = 0;
	/*
	 * 间距
	 */
	private static int SPA = 0;

	private static final int RECT_H = IMG_RECT_H+4;
	
	public LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA = (this.h - RECT_H *5 - (PADDING<<1) - Img.DB.getHeight(null))/MAX_ROM;
		START_Y = PADDING + Img.DB.getHeight(null)+SPA;
	}

	@Override
	 abstract public void paint(Graphics g) ;
	/**
	 * 绘制该窗口所有值槽
	 * 
	 * @param imgTitle 标题图片
	 * @param players 数据源
	 * @param g 画笔
	 */
	public void showData(Image imgTitle ,List<Player> players ,Graphics g) {
//		绘制标题
		g.drawImage(imgTitle, this.x+PADDING, this.y+PADDING, null);
//		获得现在分数
		int nowPoint = this.dto.getNowPoint();
//		循环绘制记录
		for (int i = 0; i < MAX_ROM; i++) {
//			获得一条玩家记录
			Player player = players.get(i);
//			值槽比值
			double percent = (double)nowPoint/player.getPoint();
//			计算现在分数与记录分数比值
			percent = percent > 1 ? 1.0: percent;
//			绘制值槽
			this.drawRect(START_Y+i*(RECT_H+SPA), player.getName(),
					Integer.toString(player.getPoint()),percent, g);
		}
	}
}
