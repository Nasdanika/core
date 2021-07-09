package org.nasdanika.emf.localization;

import org.nasdanika.common.ResourceLocator;

/**
 * Holds built-in localizations
 * @author Pavel
 *
 */
public enum UI implements ResourceLocator, PropertyKeys {
	
	RU() {

		@Override
		public Object get(String key) {
			switch (key) {
			case UI_DESCRIPTION:
				return "Описание";
			case UI_SUMMARY:
				return "Краткое описание";
			case UI_NAME:
				return "Наименование";
			case UI_TYPE:
				return "Тип";
			case UI_CARDINALITY:
				return "Мощность";
			case UI_PACKAGE:
				return "Пакет";
			case UI_SUPERTYPES:
				return "Супертипы";
			case UI_SUBTYPES:
				return "Подтипы";						
			case UI_DIAGRAM:
				return "Диаграмма";						
			case UI_CONTENTS:
				return "Содержание";						
			case UI_NAMESPACE_URI:
				return "URI пространства имён";						
			case UI_OPPOSITE:
				return "Противоположная сссылка";						
			case UI_ABSTRACT:
				return "Абстрактный";						
			case UI_INTERFACE:
				return "Интерфейс";						
			default:
				return null;
			}
		}
		
	};
	
	@Override
	public <T> T get(Class<T> type) {
		return null;
	}

}
